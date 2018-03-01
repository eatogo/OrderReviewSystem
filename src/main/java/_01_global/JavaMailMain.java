package _01_global;


import java.util.Arrays;
import java.util.List;

public class JavaMailMain {

	public static void main(String[] args) {
		String from = "rujay.svn@gmail.com";
		List<String> to = Arrays.asList(
				   new String[]{"rujay.sun@gmail.com"});
//		List<String> cc = Arrays.asList(
//				   new String[]{"xpt12591@gmail.com", "apriori306@gmail.com"});
		List<String> bcc = Arrays.asList(
				   new String[]{"rujay.svn@gmail.com"});
		String subject = "您已完成訂購";
		String text = 
		"<h3>謝謝您訂購</h3><br>"
		+ "<br><font color='blue'> 再次感謝, </font><br> "
        + "Eatogo祝您用餐愉快";
		List<String> attachment = Arrays.asList(
				   new String[]{"C:\\_JSP\\Gmail_logo.png", 
//						   "D:\\_Java\\fs.jpg",
//						   "D:\\_Java\\autumn_fs.jpg",
						   });;
//		JavaMailUtil  util = new JavaMailUtil(from, to, cc, bcc, subject, text ,attachment);
		JavaMailUtil  util = new JavaMailUtil(from, to, null, bcc, subject, text ,attachment);
		if (util.send()){
		   System.out.println("發信成功");
		} else {
		   System.out.println("發信失敗");
		}
	}

}
