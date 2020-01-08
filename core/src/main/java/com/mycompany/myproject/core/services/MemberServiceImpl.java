package com.mycompany.myproject.core.services;

//DS Annotations
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

//SQL import statements 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import com.day.commons.datasource.poolservice.DataSourcePool;
import javax.sql.DataSource;

// import data model
import com.mycompany.myproject.core.models.MemberModel;

@Component
public class MemberServiceImpl implements MemberService {

    /** Default log. */
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Reference
    private DataSourcePool source;

    // Get Customer Data from MySQL
    public String getMembers() {

        DataSource dataSource = null;
        Connection c = null;
        String query = "";
        MemberModel member = null;
        List<MemberModel> memberList = new ArrayList<MemberModel>();
        try {

            // Query data from the Employee table located in MySQL
            c = getConnection();

            ResultSet rs = null;
            Statement s = c.createStatement();
            Statement scount = c.createStatement();

            PreparedStatement pstmt = null;
            PreparedStatement ps = null;

            // Specify the SQL Statement to query data from, the empployee table
            query = "Select first_name,last_name,city FROM member";

            pstmt = c.prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                member = new MemberModel();

                member.setFirstName(rs.getString(1));
                member.setLastName(rs.getString(2));
                member.setCity(rs.getString(3));

                memberList.add(member);
            }

            return convertToString(toXml(memberList));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private Document toXml(List<MemberModel> memberList) {
        // convert to an xml document

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        // Start building the XML to pass back to the AEM client
        Element root = doc.createElement("Members");
        doc.appendChild(root);

        int custCount = memberList.size();


        // TODO create all the xml elements
        for (int i = 0; i < custCount; i++) {
            // TODO create all the xml elements
        }

        return doc;

    }

    private String convertToString(Document xml) {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            StreamResult result = new StreamResult(new StringWriter());
            DOMSource source = new DOMSource(xml);
            transformer.transform(source, result);
            return result.getWriter().toString();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // Returns a connection using the configured DataSourcePool
    private Connection getConnection() {
        DataSource dataSource = null;
        Connection con = null;
        try {
            // Inject the DataSourcePool right here!
            log.info("GET CONNECTION");
            dataSource = (DataSource) source.getDataSource("Member");
            con = dataSource.getConnection();

            log.info("CONNECTION is returned");
            return con;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}