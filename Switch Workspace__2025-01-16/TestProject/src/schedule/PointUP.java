package schedule;

import java.util.ArrayList;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import dao.MemberDao;
import dto.MemberProfileDto;

public class PointUP implements Job {
    @Override
    public void execute(JobExecutionContext ctx) throws JobExecutionException {
    	MemberDao dao = new MemberDao();
    	ArrayList<MemberProfileDto> list = null; 
    	try {
			list = dao.showMember();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	int count = 0;
    	for(MemberProfileDto dto : list) {
    		int point = dto.getPoint() +1;
    		try {
				dao.payPoint(point,dto.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
    		count++;
    	}
    	System.out.println("스케줄러가 실행됨"+count+"명에게 포인트 부여(1점).");
    }
}