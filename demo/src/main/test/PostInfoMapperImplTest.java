import com.learn.entity.PostInfo;
import com.learn.mapper.impl.PostInfoMapperImpl;
import org.junit.Test;

import java.util.List;
//单元测试类测试数据库的访问方法是否有效
public class PostInfoMapperImplTest {
    @Test
    public void testInsert(){
        PostInfoMapperImpl mapper=new PostInfoMapperImpl();
        PostInfo postInfo=new PostInfo("军事sss","2019-12-1 12:1:1",12,"kkkkkk",1,"sss.aa");
        mapper.insert(postInfo);
    }
    @Test
    public void testDelete(){
        PostInfoMapperImpl mapper=new PostInfoMapperImpl();
        PostInfo postInfo=new PostInfo(18,"军事sss","2019-12-1 12:1:1",12,"kkkkkk",1,"sss.aa");
        mapper.delete(postInfo);
    }
    @Test
    public void testUpdate(){
        PostInfoMapperImpl mapper=new PostInfoMapperImpl();
        PostInfo postInfo=new PostInfo(18,"klasss","2019-12-1 12:1:1",12,"kkkkkk",1,"sss.aa");
        mapper.update(postInfo);
    }
    @Test
    public void testSelect(){
        PostInfoMapperImpl mapper=new PostInfoMapperImpl();
        PostInfo select = mapper.select(1);
        System.out.println(select);
    }
    @Test
    public void testSelectAll(){
        PostInfoMapperImpl mapper=new PostInfoMapperImpl();
        List<PostInfo> postInfos = mapper.selectAll();
        for (PostInfo postInfo:postInfos){
            System.out.println(postInfo);
        }
    }
}
