package com.mycompany.myproject.core.services;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.ArrayList;

import com.mycompany.myproject.core.models.MemberModel;

class MemberServiceImplTest {

    private MemberServiceImpl memberService = new MemberServiceImpl();

    private List<MemberModel> members = new ArrayList<MemberModel>();

    // setup some data
    public void setup() {
        MemberModel m1 = new MemberModel();
        m1.setFirstName("a");
        m1.setLastName("b");
        m1.setCity("syd");

        MemberModel m2 = new MemberModel();
        m2.setFirstName("aa");
        m2.setLastName("bb");
        m2.setCity("mel");

        members.add(m1);
        members.add(m2);
    }

    @Test
    void testGetMembers() {
        // mock jdbc part
        // spy getMembers();
        // test the result;
    }
}