package com.scau.pbook.pane;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.scau.pbook.bean.AddressBean;
import com.scau.pbook.service.AddressService;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
/**
 * ²âÊÔ
 * @author Administrator
 *
 */
public class Test extends Application {
    public static void main(String[] args) {
    	Application.launch(args);
    }
	
	public void start(Stage primaryStage) throws Exception {
		Button bt = new Button("bt");
		Pane pane = new Pane();
		pane.getChildren().add(bt);
		Scene scene = new Scene(pane,200,200);
		primaryStage.setTitle("hello");
		primaryStage.setScene(scene);
		primaryStage.show();
		bt.setOnAction(e->{
			FileChooser fileChooser1 = new FileChooser();
			fileChooser1.setInitialFileName("Í¨Ñ¶Â¼.csv");
			fileChooser1.getExtensionFilters().addAll(new ExtensionFilter("CSV", "*.csv"),new ExtensionFilter("Vcard", "*.vcf"));
			fileChooser1.setTitle("Save Image");
			File file = fileChooser1.showSaveDialog(primaryStage);
			List<AddressBean> list = new ArrayList<>();
			AddressBean bean = new AddressBean();
			bean.setName("a");
			bean.setTelephone("a");
			bean.setMobilephone("te");
			bean.setEmail("e");
			bean.setAddress("a");
			bean.setWorkplace("e");
			bean.setGroup("a");
			bean.setPostcode("a");
			bean.setRemarks("e");
			bean.setBirthday("e");
			list.add(bean);
	        AddressService service = new AddressService(file);
	       // service.exportFile(list);
			
		});

	}

}
