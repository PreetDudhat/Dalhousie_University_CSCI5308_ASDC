package com.example.BrighterSpace.dataLayer.mocks.implementation;

import com.example.BrighterSpace.dataLayer.mocks.IFaqDAO;
import com.example.BrighterSpace.model.Faq;

import java.util.ArrayList;
import java.util.List;

public class FaqDAOMock implements IFaqDAO {

    @Override
    public List<Faq> getAllFaqsByCourseId(int courseId) {
        List<Faq> list = new ArrayList<>();
        Faq f1 = new Faq(1, "When is the exam?", "Its on this Saturday", 1);
        list.add(f1);
        return list;
    }

    @Override
    public String createFaq(Faq faq, int courseId) {
        return "FAQ Created!";
    }

    @Override
    public Faq getFaqById(int faqId) {
        Faq f1 = new Faq(1, "When is the exam?", "Its on this Saturday", 1);
        return f1;
    }

    @Override
    public Faq updateFaqById(Faq faq) {
        Faq f1 = new Faq(1, "When is the exam?", "Its on this Saturday", 1);
        return f1;
    }

    @Override
    public String deleteFaqById(int faqId) {
        return "FAQ Deleted!";
    }
}
