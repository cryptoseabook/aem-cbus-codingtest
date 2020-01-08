package com.mycompany.myproject.core.servlets;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletRequest;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(AemContextExtension.class)
class SimpleServletTest {

    private SimpleServlet fixture = new SimpleServlet();

    @Mock
    private MemberService memberService;

    @Test
    void doGet(AemContext context) throws ServletException, IOException {
        when(memberService.getMembers()).thenReturn("members");

        MockSlingHttpServletRequest request = context.request();
        MockSlingHttpServletResponse response = context.response();

        fixture.doGet(request, response);

        assertEquals("members", response.getOutputAsString());
    }
}