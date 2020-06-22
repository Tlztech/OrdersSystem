package test;

import page.pagetools.FtpUtils;

public class UploadYahooImg {

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 8; i++) {
			System.out.println("D:\\yahoo\\tuikaimg\\zip\\img\\image" + i + ".zip");
			if (i > 8) {
				Thread.sleep(200000);
			}
			String host = "yjftp.yahoofs.jp";

			String user = "store-coverforefront";
			String pwd = "i7AlE39p";
			String local = "";
			String remote = "";

			remote = "lib_img.zip";
			local = ("D:\\yahoo\\tuikaimg\\zip\\img\\image" + i + ".zip");
			String[] argggg = new String[] { "-s", host, user, pwd, remote, local };
			FtpUtils.ftpUtils(argggg);
			
		}
	}

}
