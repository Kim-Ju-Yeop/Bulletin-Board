package database;

import model.BulletinGoodModel;
import model.BulletinModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SimpleDbBulletin {

    public void defaultDb(JdbcStrategy jdbcStrategy) throws Exception{
        Connection con;
        PreparedStatement pstmt;

        Class.forName("org.mariadb.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mariadb://211.53.209.159/dgsw_java", "dgsw_student", "dgswjava");

        pstmt = jdbcStrategy.makePreparedStatement(con);
        pstmt.executeQuery();
    }

    public List<BulletinModel> getAllList() throws Exception{
        List<BulletinModel> list = new ArrayList<>();

        defaultDb(new JdbcStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(Connection con) throws SQLException {
                StringBuilder sql = new StringBuilder();
                sql.append("SELECT ");
                sql.append("id, ");
                sql.append("hit, ");
                sql.append("title, ");
                sql.append("content, ");
                sql.append("writer ");
                sql.append("FROM bulletin ");
                sql.append("ORDER BY id DESC ");

                PreparedStatement pstmt = con.prepareStatement(sql.toString());
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    int hit = rs.getInt("hit");
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    String writer = rs.getString("writer");

                    BulletinModel listModel = new BulletinModel(id, hit, title, content, writer);
                    list.add(listModel);
                }
                return pstmt;
            }
        });
        return list;
    }

    public BulletinModel getList(int id) throws Exception{
        BulletinModel[] model = new BulletinModel[1];

        defaultDb(new JdbcStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(Connection con) throws SQLException {
                StringBuilder sql = new StringBuilder();
                sql.append("SELECT ");
                sql.append("id, ");
                sql.append("hit, ");
                sql.append("title, ");
                sql.append("content, ");
                sql.append("writer ");
                sql.append("FROM bulletin ");
                sql.append("WHERE id LIKE ?");

                PreparedStatement pstmt = con.prepareStatement(sql.toString());
                pstmt.setString(1, String.valueOf(id));

                ResultSet rs = pstmt.executeQuery();
                rs.next();

                model[0] = new BulletinModel(rs.getInt("id"), rs.getInt("hit"), rs.getString("title"),
                        rs.getString("content"), rs.getString("writer"));
                return pstmt;
            }
        });
        return model[0];
    }

    public void writeList(BulletinModel model) throws Exception{
        defaultDb(new JdbcStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(Connection con) throws SQLException {
                StringBuilder sql = new StringBuilder();
                sql.append("INSERT INTO bulletin ");
                sql.append("(id, hit, title, content, writer) ");
                sql.append("VALUES (?, ?, ?, ?, ?) ");

                PreparedStatement pstmt = con.prepareStatement(sql.toString());
                pstmt.setInt(1, model.id);
                pstmt.setInt(2, model.hit);
                pstmt.setString(3, model.title);
                pstmt.setString(4, model.content);
                pstmt.setString(5, model.writer);

                return pstmt;
            }
        });
    }

    public void updateList(BulletinModel model) throws Exception{
        defaultDb(new JdbcStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(Connection con) throws SQLException {
                StringBuilder sql = new StringBuilder();
                sql.append("UPDATE bulletin SET ");
                sql.append("title = ?, ");
                sql.append("content = ? ");
                sql.append("WHERE id = ? ");

                PreparedStatement pstmt = con.prepareStatement(sql.toString());
                pstmt.setString(1, model.title);
                pstmt.setString(2, model.content);
                pstmt.setInt(3, model.id);

                return pstmt;
            }
        });
    }

    public void deleteList(int id) throws Exception{
        defaultDb(new JdbcStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(Connection con) throws SQLException {
                StringBuilder sql = new StringBuilder();
                sql.append("DELETE ");
                sql.append("FROM bulletin ");
                sql.append("WHERE id = ? ");

                PreparedStatement pstmt = con.prepareStatement(sql.toString());
                pstmt.setInt(1, id);

                return pstmt;
            }
        });
    }

    public int getId() throws Exception{
        int[] id = new int[1];

        defaultDb(new JdbcStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(Connection con) throws SQLException {
                StringBuilder sql = new StringBuilder();
                sql.append("SELECT ");
                sql.append("id ");
                sql.append("FROM bulletin ");
                sql.append("ORDER BY id DESC ");

                PreparedStatement pstmt = con.prepareStatement(sql.toString());
                ResultSet rs = pstmt.executeQuery();
                rs.next();

                try{
                    id[0] = rs.getInt("id");
                }catch (Exception e){
                    id[0] = 0;
                }

                return pstmt;
            }
        });
        return id[0];
    }

    public int getHit(int id) throws Exception{
        int[] hit = new int[1];

        defaultDb(new JdbcStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(Connection con) throws SQLException {
                StringBuilder sql = new StringBuilder();
                sql.append("SELECT ");
                sql.append("hit ");
                sql.append("FROM bulletin ");
                sql.append("WHERE id = ? ");

                PreparedStatement pstmt = con.prepareStatement(sql.toString());
                pstmt.setInt(1, id);

                ResultSet rs = pstmt.executeQuery();
                rs.next();

                try{
                    hit[0] = rs.getInt("hit");
                }catch (Exception e){
                    hit[0] = 0;
                }

                return pstmt;
            }
        });
        return hit[0];
    }

    public void updateHit(int hit, int id) throws Exception{
        int addHit = ++hit;

        defaultDb(new JdbcStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(Connection con) throws SQLException {
                StringBuilder sql = new StringBuilder();
                sql.append("UPDATE bulletin SET ");
                sql.append("hit = ? ");
                sql.append("WHERE id = ? ");

                PreparedStatement pstmt = con.prepareStatement(sql.toString());
                pstmt.setInt(1, addHit);
                pstmt.setInt(2, id);

                return pstmt;
            }
        });
    }

    public List<BulletinGoodModel> getHitUserList(int id) throws Exception{
        List<BulletinGoodModel> list = new ArrayList<>();

        defaultDb(new JdbcStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(Connection con) throws SQLException {
                StringBuilder sql = new StringBuilder();
                sql.append("SELECT ");
                sql.append("bulletin_id, ");
                sql.append("writer_id, ");
                sql.append("hit_time ");
                sql.append("FROM bulletin_good ");
                sql.append("WHERE bulletin_id LIKE ?");

                PreparedStatement pstmt = con.prepareStatement(sql.toString());
                pstmt.setString(1, String.valueOf(id));

                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    int bulletin_id = rs.getInt("bulletin_id");
                    String writer_id = rs.getString("writer_id");
                    Date hit_time = rs.getDate("hit_time");

                    BulletinGoodModel listModel = new BulletinGoodModel(bulletin_id, writer_id, hit_time);
                    list.add(listModel);
                }
                return pstmt;
            }
        });
        return list;
    }
}
