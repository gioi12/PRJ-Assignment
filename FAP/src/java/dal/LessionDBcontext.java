/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Attendence;
import entity.GroupStudent;
import entity.Lecture;
import entity.Lession;
import entity.Room;
import entity.Slot;
import entity.Student;
import entity.Subject;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ACER
 */
public class LessionDBcontext extends DBcontext<Lession> {

    public ArrayList<Attendence> getAttsById(int lesId) {
        ArrayList<Attendence> atts = new ArrayList<>();
        try {
            String sql = "SELECT \n"
                    + "                  les.leid,s.sid,s.sname,\n"
                    + "                  a.aid,a.description,a.isPresent,a.capturedtime   \n"
                    + "                  FROM \n"
                    + "                  Student s INNER JOIN Enrollment e ON s.[sid] = e.[sid]\n"
                    + "							INNER JOIN StudentGroup g ON g.gid = e.gid \n"
                    + "							INNER JOIN Lession les ON les.gid = g.gid\n"
                    + "                   			LEFT JOIN Attendence a ON a.leid = les.leid AND s.sid = a.sid\n"
                    + "                    WHERE les.leid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, lesId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {                
                Attendence a = new Attendence();
                Lession lession = new Lession();
                lession.setId(rs.getInt("leid"));
                a.setLession(lession);
                
                Student s = new Student();
                s.setId(rs.getInt("sid"));
                s.setName(rs.getString("sname"));
                a.setStudent(s);
                a.setId(rs.getInt("aid"));
                if(a.getId() != 0)
                {
                    a.setDescription(rs.getString("description"));
                    a.setPresent(rs.getBoolean("isPresent"));
                    a.setTime(rs.getTimestamp("capturedtime"));
                }
                atts.add(a);
             
            }
            return atts;
        } catch (SQLException ex) {
            Logger.getLogger(LessionDBcontext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return atts;
        
    }

    public ArrayList<Lession> getBy(int lid, Date from, Date to) {
        ArrayList<Lession> lessions = new ArrayList<>();
        try {
            String sql = "SELECT  \n"
                    + "	les.leid,les.isAttended,les.date,\n"
                    + "	g.gid,g.gname,su.subid,su.suname,\n"
                    + "	t.tid,t.tname,\n"
                    + "	r.rid,r.rname,\n"
                    + "	l.lid,l.lname\n"
                    + "FROM Lession les INNER JOIN StudentGroup g ON g.gid = les.gid\n"
                    + "				 INNER JOIN [Subject] su ON su.subid = g.subid\n"
                    + "				 INNER JOIN TimeSlot t ON t.tid = les.tid\n"
                    + "				 INNER JOIN Room r ON r.rid = les.rid\n"
                    + "				 INNER JOIN Lecturer l ON l.lid = les.lid\n"
                    + "WHERE les.lid=? AND les.[date] >=? AND les.[date]<=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, lid);
            stm.setDate(2, from);
            stm.setDate(3, to);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Lession les = new Lession();
                GroupStudent g = new GroupStudent();
                Subject su = new Subject();
                Slot slot = new Slot();
                Room room = new Room();
                Lecture lec = new Lecture();

                les.setId(rs.getInt("leid"));
                les.setAttended(rs.getBoolean("isAttended"));
                les.setDate(rs.getDate("date"));

                g.setId(rs.getInt("gid"));
                g.setName(rs.getString("gname"));
                su.setId(rs.getInt("subid"));
                su.setName(rs.getString("suname"));
                g.setSubject(su);

                les.setGroup(g);

                slot.setId(rs.getInt("tid"));
                slot.setName(rs.getString("tname"));
                les.setSlot(slot);

                room.setId(rs.getInt("rid"));
                room.setName(rs.getString("rname"));
                les.setRoom(room);

                lec.setLid(rs.getInt("lid"));
                lec.setLname(rs.getString("lname"));
                les.setLecturer(lec);

                lessions.add(les);
            }

        } catch (SQLException ex) {
            Logger.getLogger(LessionDBcontext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lessions;
    }

    public ArrayList<Lession> getInfoTimeTable(int lid, java.sql.Date from, java.sql.Date to) {
        ArrayList<Lession> lessions = new ArrayList<>();
        try {
            String sql = "SELECT  \n"
                    + "                    les.leid,les.isAttended,les.date,\n"
                    + "                    g.gid,g.gname,su.subid,su.suname,\n"
                    + "                   	t.tid,t.tname,\n"
                    + "                   	r.rid,r.rname,\n"
                    + "                    l.lid,l.lname\n"
                    + "                    FROM Lession les INNER JOIN StudentGroup g ON g.gid = les.gid\n"
                    + "                   		 INNER JOIN [Subject] su ON su.subid = g.subid\n"
                    + "                    			 INNER JOIN TimeSlot t ON t.tid = les.tid\n"
                    + "                   			 INNER JOIN Room r ON r.rid = les.rid\n"
                    + "                    			 INNER JOIN Lecturer l ON l.lid = les.lid\n"
                    + "                     WHERE les.lid= ? AND les.[date] >=? AND les.[date]<=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            stm.setInt(1, lid);
            stm.setDate(2, from);
            stm.setDate(3, to);
            while (rs.next()) {
                Lession les = new Lession();
                GroupStudent g = new GroupStudent();
                Subject su = new Subject();
                Slot slot = new Slot();
                Room room = new Room();
                Lecture lec = new Lecture();

                les.setId(rs.getInt("leid"));
                les.setAttended(rs.getBoolean("isAttended"));
                les.setDate(rs.getDate("date"));

                g.setId(rs.getInt("gid"));
                g.setName(rs.getString("gname"));
                su.setId(rs.getInt("subid"));
                su.setName(rs.getString("suname"));
                g.setSubject(su);

                les.setGroup(g);

                slot.setId(rs.getInt("tid"));
                slot.setName(rs.getString("tname"));
                les.setSlot(slot);

                room.setId(rs.getInt("rid"));
                room.setName(rs.getString("rname"));
                les.setRoom(room);

                lec.setLid(rs.getInt("lid"));
                lec.setLname(rs.getString("lname"));
                les.setLecturer(lec);

                lessions.add(les);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LessionDBcontext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lessions;

    }

    @Override
    public ArrayList<Lession> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Lession entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Lession entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Lession entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
