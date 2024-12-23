package online.proyi.springbot3.dao;

import online.proyi.springbot3.dao.provider.UserDaoProvider;
import online.proyi.springbot3.entity.User;
import online.proyi.springbot3.entity.query.UserQueryBean;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface IUserDao {
    String SELECT_USER_SQL = "select u.id, u.password, u.user_name, u.email, u.phone_number, u.description, u.create_time, u.update_time from tb_user u";

    @Results(
            id = "UserResult",
            value = {
                    @Result(property = "id", column = "id"),
                    @Result(property = "userName", column = "user_name"),
                    @Result(property = "password", column = "password"),
                    @Result(property = "email", column = "email"),
                    @Result(property = "phoneNumber", column = "phone_number"),
                    @Result(property = "description", column = "description"),
                    @Result(property = "createTime", column = "create_time"),
                    @Result(property = "updateTime", column = "update_time")
            }
    )
    @Select({SELECT_USER_SQL, " where id = #{id}"})
    User findById(@Param("id") Long id);

    @ResultMap("UserResult")
    @Select(SELECT_USER_SQL)
    User findAll();

    @ResultMap("UserResult")
    @Select("""
            <script>
                select u.id, u.password, u.user_name, u.email, u.phone_number, u.description, u.create_time, u.update_time 
                from tb_user u
                <where>
                    u.id > '0'
                    <if test="userName != null and userName != ''">
                        AND u.user_name like concat('%', #{userName}, '%')
                    </if>
                    <if test="description != null and description != ''">
                        AND u.description like concat('%', #{description}, '%')
                    </if>
                    <if test="phoneNumber != null and phoneNumber != ''">
                        AND u.phone_number like concat('%', #{phoneNumber}, '%')
                    </if>
                    <if test="email != null and email != ''">
                        AND u.email like concat('%', #{email}, '%')
                    </if>
                </where>
            </script>
            """)
    List<User> findList(UserQueryBean userQueryBean);

    @Delete("delete from tb_user where id = #{id}")
    int deleteById(Long id);

    @Delete("""
            <script>
                delete from tb_user where id in
                <foreach collection="array" item="id" open="(" separator="," close=")">
                    #{id}
                </foreach>
            </script>
            """)
    int deleteByIds(Long[] ids);

    @Update("""
        <script>
            update tb_user
             <set>
                <if test="userName != null and userName != ''">user_name = #{userName}, </if>
                <if test="email != null and email != ''"> email = #{email}, </if>
                <if test="phoneNumber != null and phoneNumber != ''"> phone_number = #{phoneNumber}, </if>
                <if test="description != null and description != ''"> description = #{description}, </if>
                update_time = sysdate()
             </set>
             where id = #{id}
        </script>
        """)
    int update(User user);

    @Insert("""
            <script>
                 insert into tb_user(
                   <if test="userName != null and userName != ''"> user_name, </if>
                   <if test="password != null and password != ''"> password, </if>
                   <if test="email != null and email != ''"> email, </if>
                   <if test="phoneNumber != null and phoneNumber != ''"> phone_number, </if>
                   <if test="description != null and description != ''"> description, </if>
                   create_time, update_time)
                values(
                   <if test="userName != null and userName != ''"> #{userName}, </if>
                   <if test="password != null and password != ''"> #{password}, </if>
                   <if test="email != null and email != ''"> #{email}, </if>
                   <if test="phoneNumber != null and phoneNumber != ''"> #{phoneNumber}, </if>
                   <if test="description != null and description != ''"> #{description}, </if>
                   sysdate(), sysdate())
            </script>
            """)
    int save(User user);

    @Update({"update tb_user set password = #{password}, update_time = sysdate()", " where id = #{id}"})
    int updatePassword(User user);

    @ResultMap("UserResult")
    @SelectProvider(type = UserDaoProvider.class, method = "findById")
    User findById2(Long id);
}
