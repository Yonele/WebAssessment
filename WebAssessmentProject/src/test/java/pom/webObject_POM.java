package pom;

import java.util.HashMap;
import java.util.Map;

import module.webAssessment;

public class webObject_POM {

	public static void SetPageObject()
	{
		Map<String, String> My_Page_Obejcts = new HashMap<String, String>();
		My_Page_Obejcts.put("userListTable","//table[@class='smart-table table table-striped']//tbody");
		My_Page_Obejcts.put("btnAddUser", "//button[@class='btn btn-link pull-right']");
		My_Page_Obejcts.put("firstName", "//input[@name='FirstName']");
		My_Page_Obejcts.put("lastName", "//input[@name='LastName']");
		My_Page_Obejcts.put("userName", "//input[@name='UserName']");
		My_Page_Obejcts.put("password", "//input[@name='Password']");
		My_Page_Obejcts.put("companyAAA", "//label[1]//input[@name='optionsRadios']");
		My_Page_Obejcts.put("companyBBB", "//label[2]//input[@name='optionsRadios']");
		My_Page_Obejcts.put("role", "//select[@name='RoleId']");
		My_Page_Obejcts.put("email", "//input[@name='Email']");
		My_Page_Obejcts.put("cell", "//input[@name='Mobilephone']");
		My_Page_Obejcts.put("btnSave", "//button[@class='btn btn-success']");
		My_Page_Obejcts.put("search", "//input[@class='pull-right ng-pristine ng-valid']");
		My_Page_Obejcts.put("addedRecord", "//table//tbody//tr//td[3]");
		
		
		webAssessment.My_Page_Obejcts=My_Page_Obejcts;
	}
}
