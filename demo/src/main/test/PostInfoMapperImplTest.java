import com.learn.entity.PostInfo;
import com.learn.mapper.PostInfoMapperI;
import com.learn.utils.GetSession;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Test;

import java.util.List;
//单元测试类测试数据库的访问方法是否有效
public class PostInfoMapperImplTest {
    SqlSession session = GetSession.getSession();
    PostInfoMapperI mapper = session.getMapper(PostInfoMapperI.class);
  /*  @Test
    public void testInsert(){
        PostInfo postInfo=new PostInfo("军事sss","2019-12-1 12:1:1",12,"kkkkkk",1,"sss.aa");
        mapper.insert(postInfo);
    }
    @Test
    public void testDelete(){
        PostInfo postInfo=new PostInfo(18,"军事sss","2019-12-1 12:1:1",12,"kkkkkk",1,"sss.aa");
        mapper.delete(postInfo);
    }
    @Test
    public void testUpdate(){
        PostInfo postInfo=new PostInfo();
        postInfo.setTitle("lop");
        postInfo.setId(16);
        mapper.update(postInfo);
    }*/
    @Test
    public void testSelect(){
        PostInfo select = mapper.select(1);
        System.out.println(select);
    }
    @Test
    public void testSelectAll(){
        List<PostInfo> postInfos = mapper.selectAll("a",2,1,10);
        for (PostInfo post:postInfos){
            System.out.println(post);
        }
    }
    @After
    public void after(){
        session.commit();
        session.close();
    }
}
