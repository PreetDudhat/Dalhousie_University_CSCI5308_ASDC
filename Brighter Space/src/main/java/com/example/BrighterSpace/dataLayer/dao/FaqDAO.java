package com.example.BrighterSpace.dataLayer.dao;

import com.example.BrighterSpace.dataLayer.mocks.IFaqDAO;
import com.example.BrighterSpace.model.Faq;
import org.springframework.context.annotation.Lazy;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FaqDAO implements IFaqDAO {
    @Lazy
    private final Connection conn = com.example.BrighterSpace.config.Connection.getInstance();

    public String createFaq(Faq faq, int courseId) throws SQLException {
        String query = "{CALL createFaq(?, ?, ?)}";
        try {
            CallableStatement statement = conn.prepareCall(query);
            statement.setString(1, faq.getFaqQuestion());
            statement.setString(2, faq.getFaqAnswer());
            statement.setInt(3, courseId);
            statement.executeQuery();
            return "Faq added";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conn.close();
        }
    }


    public Faq getFaqById(int faqId) throws SQLException {
        String query = "{CALL GetFaqById(?)}";
        Faq faq = null;
        try {
            CallableStatement statement = conn.prepareCall(query);
            statement.setInt(1, faqId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                faq = new Faq(resultSet.getInt("faqId"),
                        resultSet.getString("faqQuestion"), resultSet.getString("faqAnswer"),
                        resultSet.getInt("course_id"));
            }
            return faq;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conn.close();
        }
    }

    public Faq updateFaqById(Faq faq) throws SQLException {
        String query = "{CALL UpdateFaq(?, ?, ?, ?)}";
        try {
            CallableStatement statement = conn.prepareCall(query);
            statement.setInt(1, faq.getFaqId());
            statement.setString(2, faq.getFaqQuestion());
            statement.setString(3, faq.getFaqAnswer());
            statement.setInt(4, faq.getCourseId());
            statement.executeUpdate();
            return faq;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conn.close();
        }
    }

    public String deleteFaqById(int faqId) throws SQLException {
        String query = "{CALL DeleteFaqById(?)}";
        try {
            CallableStatement statement = conn.prepareCall(query);
            statement.setInt(1, faqId);
            statement.execute();
            return "FAQ deleted";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conn.close();
        }
    }


    public List<Faq> getAllFaqsByCourseId(int courseId) throws SQLException {
        List<Faq> faqs = new ArrayList<>();
        String query = "{CALL GetAllFaqsByCourseId(?)}";
        try {
            CallableStatement statement = conn.prepareCall(query);
            statement.setInt(1, courseId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Faq faq = new Faq(resultSet.getInt("faqId"),
                        resultSet.getString("faqQuestion"), resultSet.getString("faqAnswer"),
                        resultSet.getInt("course_Id"));
                faqs.add(faq);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conn.close();
        }
        return faqs;
    }
}

