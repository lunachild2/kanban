package models.member;

import configs.DBConnection;
import org.apache.ibatis.session.SqlSession;
import org.mindrot.jbcrypt.BCrypt;

public class UsersDao {
    public boolean register(Users users) {
        SqlSession sqlSession = DBConnection.getSession();
        String hash = BCrypt.hashpw(users.getUserPw(), BCrypt.gensalt(12));
        users.setUserPw(hash);

        int affectedRows = sqlSession.insert("UserMapper.add", users);

        sqlSession.commit();

        return affectedRows > 0;
    }

    public Users get(String userId) {
        SqlSession sqlSession = DBConnection.getSession();

        Users params = new Users();
        params.setUserId(userId);

        Users users = sqlSession.selectOne("UserMapper.info", params);

        return users;
    }

    public boolean exists(String userId) {

    }
}
