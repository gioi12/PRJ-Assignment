/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Attendence;
import entity.Lession;
import entity.Student;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ACER
 */
public class AttendenceDBcontext extends DBcontext<Attendence> {

    public void batchupdateByLession(int leid, ArrayList<Attendence> atts) {
        try {
            connection.setAutoCommit(false);
            String sql_remove_atts = "DELETE Attendence WHERE leid = ?";
            PreparedStatement stm_remove_atts = connection.prepareStatement(sql_remove_atts);
            stm_remove_atts.setInt(1, leid);
            stm_remove_atts.executeUpdate();

            String sql_insert_att = "INSERT INTO [Attendence]\n"
                    + "           ([leid]\n"
                    + "           ,[sid]\n"
                    + "           ,[description]\n"
                    + "           ,[isPresent]\n"
                    + "           ,[capturedtime])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,GETDATE())";
            for (Attendence att : atts) {
                PreparedStatement stm_insert_att = connection.prepareStatement(sql_insert_att);
                stm_insert_att.setInt(1, leid);
                stm_insert_att.setInt(2, att.getStudent().getId());
                stm_insert_att.setString(3, att.getDescription());
                stm_insert_att.setBoolean(4, att.isPresent());
                stm_insert_att.executeUpdate();
            }
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(AttendenceDBcontext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AttendenceDBcontext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AttendenceDBcontext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void uppdateLession(int leid) {
        try {
            String sql = "UPDATE [Lession]\n"
                    + "   SET \n"
                    + "      [isAttended] = 'true'\n"
                    + " WHERE leid=?";
            PreparedStatement stm;
            stm = connection.prepareStatement(sql);
            stm.setInt(1, leid);
            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AttendenceDBcontext.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Attendence getInfoStudentStatusByLession(int leid, int sid) {
        Attendence att = new Attendence();
        try {
            String sql = " SELECT a.aid,a.sid,a.leid,a.isPresent,a.description,a.capturedtime FROM Attendence a\n"
                    + " WHERE leid = ? AND sid = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, leid);
            stm.setInt(2, sid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("sid"));
                att.setStudent(student);
                att.setId(rs.getInt("aid"));
                Lession les = new Lession();
                les.setId(leid);
                att.setLession(les);
                att.setPresent(rs.getBoolean("isPresent"));
                att.setDescription(rs.getString("description"));
                att.setTime(rs.getTime("capturetime"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendenceDBcontext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return att;
    }

    public boolean isAttStatusByLession(int leid) {
        ArrayList<Attendence> atts = new ArrayList<>();
        try {
            String sql = "  SELECT  [leid]\n"
                    + "      ,[gid]\n"
                    + "      ,[tid]\n"
                    + "      ,[rid]\n"
                    + "      ,[lid]\n"
                    + "      ,[date]\n"
                    + "      ,[isAttended]\n"
                    + "  FROM [Lession]\n"
                    + "  where leid = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, leid);
            ResultSet rs = stm.executeQuery();
           if (rs.next()) { // Di chuyển con trỏ tới dòng đầu tiên
            return rs.getBoolean("isAttended");
        }

        } catch (SQLException ex) {
            Logger.getLogger(AttendenceDBcontext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<Attendence> getInfoAttStatusByLession(int leid, int sid) {
        ArrayList<Attendence> atts = new ArrayList<>();
        try {
            String sql = " SELECT a.aid,a.sid,a.leid,a.isPresent,a.description,a.capturedtime FROM Attendence a\n"
                    + " WHERE leid = ? AND sid = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, leid);
            stm.setInt(2, sid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Attendence att = new Attendence();
                Student student = new Student();
                student.setId(rs.getInt("sid"));
                att.setStudent(student);
                att.setId(rs.getInt("aid"));
                Lession les = new Lession();
                les.setId(leid);
                att.setLession(les);
                att.setPresent(rs.getBoolean("isPresent"));
                att.setDescription(rs.getString("description"));
                att.setTime(rs.getTime("capturetime"));
                atts.add(att);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendenceDBcontext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return atts;
    }

    public void uppdateByChange(int leid, int sid, String note, boolean isPresent) {
        try {
            String sql = "UPDATE [Attendence]\n"
                    + "   SET \n"
                    + "       [description] = ?\n"
                    + "      ,[isPresent] = ?\n"
                    + "      ,[capturedtime] =  GETDATE()\n"
                    + " WHERE leid = ? AND sid = ?\n";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, note);
            stm.setBoolean(2, isPresent);
            stm.setInt(3, leid);
            stm.setInt(4, sid);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AttendenceDBcontext.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public ArrayList<Attendence> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Attendence entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Attendence entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Attendence entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
