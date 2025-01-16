package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import schedule.PointUP;

/**
 * Servlet implementation class SchedulerServlet
 */
@WebServlet("/SchedulerServlet")
public class SchedulerServlet extends HttpServlet {
    private static Scheduler scheduler;

    @Override
    public void init() throws ServletException {
        try {
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            scheduler = schedulerFactory.getScheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("start".equals(action)) {
            try {
                startScheduler();
                response.getWriter().write("Scheduler started");
                System.out.println("<<< 포인트 스케줄러가 시작되었습니다. >>>");
            } catch (SchedulerException e) {
                response.getWriter().write("Scheduler start error");
                e.printStackTrace();
            }
        } else if ("stop".equals(action)) {
            try {
                stopScheduler();
                response.getWriter().write("Scheduler stopped");
                System.out.println("<<< 포인트 스케줄러가 정지되었습니다. >>>");
            } catch (SchedulerException e) {
                response.getWriter().write("Scheduler stop error");
                e.printStackTrace();
            }
        }
    }

    private void startScheduler() throws SchedulerException {
        // 스케줄러가 종료되었으면 새로 초기화
        if (scheduler == null || scheduler.isShutdown()) {
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            scheduler = schedulerFactory.getScheduler();
        }

        JobDetail jobDetail = JobBuilder.newJob(PointUP.class)
                                        .withIdentity("PointUP", "group1")
                                        .build();

        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                                                .withIdentity("cronTrigger", "group1")
                                                .withSchedule(CronScheduleBuilder.cronSchedule("0/20 * * * * ?")) // 매 20초마다 실행
                                                .build();

        scheduler.scheduleJob(jobDetail, cronTrigger);
        scheduler.start();
    }

    private void stopScheduler() throws SchedulerException {
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdown();
        }
    }
}
