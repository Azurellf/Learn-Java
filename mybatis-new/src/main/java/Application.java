import Dao.Employee;
import mapper.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        SqlSession sqlSession = MyBatisUtil.getSession();
        try {
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            List<Employee> employees = employeeMapper.getSomeEmployee();
            System.out.println(employees);
        }finally {
            sqlSession.close();
        }
    }
}
