package com.example.todolist;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.ui.Label;
import com.vaadin.annotations.Theme;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.*;
import com.mysql.MysqlJavaConnector;
import java.sql.*;

import java.sql.Connection;

@Theme("TODOLIST")
public class TODOLISTUI extends UI{

    @Override
    protected void init(VaadinRequest request){

        Connection connection =  MysqlJavaConnector.getConnection();

        System.out.println(connection);





        VerticalLayout content = new VerticalLayout();
        setContent(content);

        content.setSizeFull();
        setSizeFull();

        FormLayout formLayout = new FormLayout();

        formLayout.setMargin(true);

        content.addComponent(formLayout);
        content.setExpandRatio(formLayout, 1);

        formLayout.addComponent(new Label("TO-DO LIST"));

        TextField typeItem = new TextField("Name");
        formLayout.addComponent(typeItem);

        Button addItemButton = new Button("Add new item");
        formLayout.addComponent(addItemButton);

        addItemButton.addClickListener(e -> {
            //Label itemLabel = new Label(typeItem.getValue());
            //formLayout.addComponent(itemLabel);
            Statement st = null;
            String itemValue = typeItem.getValue();


            try {

                st = connection.createStatement();

                //PreparedStatement insert = connection.prepareStatement("INSERT INTO managelist(name)VALUES (itemValue)");
                //insert.setString(1,itemValue);

                String query = "INSERT INTO list (name) " +"VALUES ('"+itemValue+"')";

                st.executeUpdate(query);
                //insert.execute();

                PreparedStatement item = connection.prepareStatement("SELECT*FROM list");

                ResultSet result = item.executeQuery();
                int count = 0;

                while(result.next()){
                    // System.out.println(result.getString("name"));

                    if (count == 0) {

                        Label itemLabel = new Label(result.getString("name"));
                        formLayout.addComponent(itemLabel);
                    }

                    else if (count>=1){

                        PreparedStatement newItem = connection.prepareStatement("SELECT * FROM list WHERE id = (SELECT MAX(id) FROM list)");

                        ResultSet newResult = newItem.executeQuery();
                        Label newItemLabel = new Label(newResult.getString("name"));
                        formLayout.addComponent(newItemLabel);
                    }
                    count++;
                }

                // connection.close();
              /*  Label tableItem = new Label();
                formLayout.addComponent(tableItem);*/

                // as long as the debugger stops at acertain break point the ide will show the values of variables on the green stuff

            } catch (SQLException e1) {
                e1.printStackTrace();
            }


        });



        content.setComponentAlignment(formLayout, Alignment.TOP_CENTER);




    }


    @Override
    public void close() {
        super.close();
        MysqlJavaConnector.close();
    }
}





