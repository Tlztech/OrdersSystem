package test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.util.Utility;

public class ttetstsfsd {

	public static void main(String[] args) throws Exception {
		List<String[]> dateArr = Utility.readCsvFileJpn(new File("D:\\item20160325213537.csv"), true);
		String picUrl = "";
		boolean ariFlg = false;
		List<String[]> picList = new ArrayList<String[]>();
		for (String[] arr : dateArr) {
			String shohinbango = convertBango(arr[33]);
			String firstimg = arr[28].split(":1:0:|;")[0];
			String shohinsetumeibun = arr[20];
			while (shohinsetumeibun.contains(".jpg")) {
				shohinsetumeibun = shohinsetumeibun.substring(shohinsetumeibun.indexOf("http"));
				picUrl = shohinsetumeibun.substring(0, shohinsetumeibun.indexOf(".jpg") + 4);
				shohinsetumeibun = shohinsetumeibun.substring(shohinsetumeibun.indexOf(".jpg") + 4);
				for (int j = 0; j < picList.size(); j++) {
					if (picUrl.equals(picList.get(j))) {
						ariFlg = true;
						break;
					}
				}
				if (!ariFlg) {
					picList.add(new String[] { picUrl, shohinbango });
				}
				ariFlg = false;
			}
		}
		downloadPic(picList);

	}

	public static void downloadPic(List<String[]> picList) {
		File file = null;
		String path = null;
		List<String> errList = new ArrayList<String>();
		for (int i = 0; i < picList.size(); i++) {
			String picUrl = picList.get(i)[0];
			try {

				path = picList.get(i)[1];

				file = new File("D://PIC_DOWNLOAD2/" + path + "/");
				file.mkdirs();
				String kachoshi = picUrl.substring(picUrl.lastIndexOf("."));
				String picName = picUrl.substring(0, picUrl.lastIndexOf("."));
				picName = picName.substring(picUrl.lastIndexOf("/") + 1);
				picName = picName + kachoshi;
				download(picUrl, file.getPath() + "/" + picName);
			} catch (Exception e) {
				errList.add(picUrl + " 下载失败！");
			}
		}

		for (int i = 0; i < errList.size(); i++) {
			System.out.println(errList.get(i));
		}
	}

	private static void download(String picUrl, String path) throws Exception {

		URL url = new URL(picUrl);
		File outFile = new File(path);
		if (!outFile.exists()) {
			System.out.println("正在下载：" + picUrl);
			OutputStream os = new FileOutputStream(outFile);
			InputStream is = url.openStream();
			byte[] buff = new byte[1024];
			while (true) {
				int readed = is.read(buff);
				if (readed == -1) {
					break;
				}
				byte[] temp = new byte[readed];
				System.arraycopy(buff, 0, temp, 0, readed);
				os.write(temp);
			}
			is.close();
			os.close();
		}
	}

	private static String convertBango(String bango) {
		String result = "";
		List<String[]> dateList = new ArrayList<String[]>();
		dateList.add(new String[] { "nzlyq760", "523345442279" });
		dateList.add(new String[] { "nzlyq761", "523343774530" });
		dateList.add(new String[] { "nzlyq762", "523353064463" });
		dateList.add(new String[] { "nzlyq763", "523745846010" });
		dateList.add(new String[] { "nzlyq765", "523375617593" });
		dateList.add(new String[] { "nzlyq766", "523391470454" });
		dateList.add(new String[] { "nzlyq767", "523722079661" });
		dateList.add(new String[] { "nzlyq768", "523753564804" });
		dateList.add(new String[] { "nzlyq769", "523884175729" });
		dateList.add(new String[] { "nzlyq770", "523819466649" });
		dateList.add(new String[] { "nzlyq771", "523935526704" });
		dateList.add(new String[] { "nzlyq773", "523944368332" });
		dateList.add(new String[] { "nzlyq774", "523870671808" });
		dateList.add(new String[] { "nzlyq775", "523936269306" });
		dateList.add(new String[] { "nzlyq776", "523904892590" });
		dateList.add(new String[] { "nzlyq777", "523904892423" });
		dateList.add(new String[] { "nzlyq778", "523895266826" });
		dateList.add(new String[] { "nzlyq779", "523897454280" });
		dateList.add(new String[] { "nzlyq780", "524133874704" });
		dateList.add(new String[] { "nzlyq781", "523972557234" });
		dateList.add(new String[] { "nzlyq782", "524273574153" });
		dateList.add(new String[] { "nzlyq783", "524098408212" });
		dateList.add(new String[] { "nzlyq784", "524286261542" });
		dateList.add(new String[] { "nzlyq785", "524285130595" });
		dateList.add(new String[] { "nzlyq786", "522005080607" });
		dateList.add(new String[] { "nzlyq787", "522002143478" });
		dateList.add(new String[] { "nzlyq788", "522001870536" });
		dateList.add(new String[] { "nzlyq789", "521985831191" });
		dateList.add(new String[] { "nzlyq790", "522003920976" });
		dateList.add(new String[] { "nzlyq791", "522000933164" });
		dateList.add(new String[] { "nzlyq792", "522022816931" });
		dateList.add(new String[] { "nzlyq793", "522019758317" });
		dateList.add(new String[] { "nzlyq794", "522002827245" });
		dateList.add(new String[] { "nzlyq795", "522022316705" });
		dateList.add(new String[] { "nzlyq796", "522018069440" });
		dateList.add(new String[] { "nzlyq797", "521985831220" });
		dateList.add(new String[] { "nzlyq798", "522171051581" });
		dateList.add(new String[] { "nzlyq799", "522163229023" });
		dateList.add(new String[] { "nzlyq800", "522163421102" });
		dateList.add(new String[] { "nzlyq801", "522166396969" });
		dateList.add(new String[] { "nzlyq802", "522167232601" });
		dateList.add(new String[] { "nzlyq803", "522641851569" });
		dateList.add(new String[] { "nzlyq804", "522834766703" });
		dateList.add(new String[] { "nzlyq805", "522661413871" });
		dateList.add(new String[] { "nzlyq806", "522807812291" });
		dateList.add(new String[] { "nzlyq807", "522661637676" });
		dateList.add(new String[] { "nzlyq808", "522781583799" });
		dateList.add(new String[] { "nzlyq809", "522803222479" });
		dateList.add(new String[] { "nzwy026", "522889308859" });
		dateList.add(new String[] { "nzwy027", "522992693100" });
		dateList.add(new String[] { "nzxdk032", "522193524393" });
		dateList.add(new String[] { "nzxdk033", "522193440206" });
		dateList.add(new String[] { "nzxdk034", "522187169972" });
		dateList.add(new String[] { "nzks130", "521857171994" });
		dateList.add(new String[] { "nzks131", "522901851733" });
		dateList.add(new String[] { "nzks132", "522876143855" });
		dateList.add(new String[] { "nzks134", "522849807541" });
		dateList.add(new String[] { "nzks135", "522870669584" });
		dateList.add(new String[] { "nzks136", "522870653611" });
		dateList.add(new String[] { "nzks137", "522869998496" });
		dateList.add(new String[] { "nzks138", "522848907597" });
		dateList.add(new String[] { "nzks139", "522933326365" });
		dateList.add(new String[] { "nzks140", "522973820634" });
		dateList.add(new String[] { "nzks141", "522924526206" });
		dateList.add(new String[] { "nzks142", "522940668173" });
		dateList.add(new String[] { "nzks143", "522940668173" });
		dateList.add(new String[] { "nzks144", "522939892469" });
		dateList.add(new String[] { "nzks145", "522928640659" });
		dateList.add(new String[] { "nzks146", "522985642706" });
		dateList.add(new String[] { "nzks147", "523282119472" });
		dateList.add(new String[] { "nzks148", "523344426559" });
		dateList.add(new String[] { "nzks149", "523343774530" });
		dateList.add(new String[] { "nzks150", "523347489577" });
		dateList.add(new String[] { "nzks151", "523980980398" });
		dateList.add(new String[] { "nzks152", "522020226052" });
		dateList.add(new String[] { "nzks153", "522562311638" });
		dateList.add(new String[] { "nzks154", "522163262775" });
		dateList.add(new String[] { "nzks155", "522161757605" });
		dateList.add(new String[] { "nzks156", "522802977822" });
		dateList.add(new String[] { "nzks157", "522661693384" });
		dateList.add(new String[] { "nzctx175", "521841809791" });
		dateList.add(new String[] { "nzctx176", "523353364495" });
		dateList.add(new String[] { "nzctx193", "522973820749" });
		dateList.add(new String[] { "nzctx194", "523353920449" });
		dateList.add(new String[] { "nzctx195", "523374370416" });
		dateList.add(new String[] { "nzctx196", "523744878755" });
		dateList.add(new String[] { "nzctx197", "523910711271" });
		dateList.add(new String[] { "nzctx198", "524258935230" });
		dateList.add(new String[] { "nzctx199", "524256019918" });
		dateList.add(new String[] { "nzctx200", "522022644693" });
		dateList.add(new String[] { "nzctx201", "522166656909" });
		dateList.add(new String[] { "nzctx202", "522164086219" });
		dateList.add(new String[] { "nzctx203", "522166656848" });
		dateList.add(new String[] { "nzctx204", "522167032366" });
		dateList.add(new String[] { "nzctx205", "522802461945" });
		dateList.add(new String[] { "nzctx206", "522803630537" });
		dateList.add(new String[] { "nzwt267", "522870234511" });
		dateList.add(new String[] { "nzwt268", "522888848942" });
		dateList.add(new String[] { "nzwt269", "522889272799" });
		dateList.add(new String[] { "nzwt270", "522930180290" });
		dateList.add(new String[] { "nzwt271", "522924893442" });
		dateList.add(new String[] { "nzwt272", "522974040481" });
		dateList.add(new String[] { "nzwt273", "522973620774" });
		dateList.add(new String[] { "nzwt274", "523208237383" });
		dateList.add(new String[] { "nzwt275", "523019386845" });
		dateList.add(new String[] { "nzwt276", "522973732739" });
		dateList.add(new String[] { "nzwt277", "522974276437" });
		dateList.add(new String[] { "nzwt278", "523108220354" });
		dateList.add(new String[] { "nzwt279", "522998295666" });
		dateList.add(new String[] { "nzwt280", "523312488818" });
		dateList.add(new String[] { "nzwt281", "523312456906" });
		dateList.add(new String[] { "nzwt282", "523281744000" });
		dateList.add(new String[] { "nzwt283", "523303638229" });
		dateList.add(new String[] { "nzwt284", "523312480282" });
		dateList.add(new String[] { "nzwt285", "523312220952" });
		dateList.add(new String[] { "nzwt286", "523306162077" });
		dateList.add(new String[] { "nzwt287", "523282183542" });
		dateList.add(new String[] { "nzwt288", "523352792452" });
		dateList.add(new String[] { "nzwt289", "523322255465" });
		dateList.add(new String[] { "nzwt290", "523353736429" });
		dateList.add(new String[] { "nzwt291", "523353288537" });
		dateList.add(new String[] { "nzwt292", "523354204364" });
		dateList.add(new String[] { "nzwt293", "523744878885" });
		dateList.add(new String[] { "nzwt294", "523753344699" });
		dateList.add(new String[] { "nzwt295", "523746713528" });
		dateList.add(new String[] { "nzwt296", "523369068218" });
		dateList.add(new String[] { "nzwt297", "523360002175" });
		dateList.add(new String[] { "nzwt298", "523745546471" });
		dateList.add(new String[] { "nzwt299", "523780068998" });
		dateList.add(new String[] { "nzwt300", "523774389991" });
		dateList.add(new String[] { "nzwt301", "523775469314" });
		dateList.add(new String[] { "nzwt302", "523772622931" });
		dateList.add(new String[] { "nzwt303", "523749383830" });
		dateList.add(new String[] { "nzwt304", "523940604843" });
		dateList.add(new String[] { "nzwt305", "523782796385" });
		dateList.add(new String[] { "nzwt306", "523935298678" });
		dateList.add(new String[] { "nzwt307", "523972302654" });
		dateList.add(new String[] { "nzwt308", "523946435298" });
		dateList.add(new String[] { "nzwt309", "524007486271" });
		dateList.add(new String[] { "nzwt310", "524005789492" });
		dateList.add(new String[] { "nzwt311", "524016552239" });
		dateList.add(new String[] { "nzwt312", "524015148423" });
		dateList.add(new String[] { "nzwt313", "524008386203" });
		dateList.add(new String[] { "nzwt314", "524007894426" });
		dateList.add(new String[] { "nzwt315", "524006982320" });
		dateList.add(new String[] { "nzwt316", "524006749041" });
		dateList.add(new String[] { "nzwt317", "524006162669" });
		dateList.add(new String[] { "nzwt318", "524004559000" });
		dateList.add(new String[] { "nzwt319", "524006749041" });
		dateList.add(new String[] { "nzwt320", "524003749869" });
		dateList.add(new String[] { "nzwt321", "523979863317" });
		dateList.add(new String[] { "nzwt322", "523978519545" });
		dateList.add(new String[] { "nzwt323", "524159009311" });
		dateList.add(new String[] { "nzwt324", "524143128465" });
		dateList.add(new String[] { "nzwt325", "524133278738" });
		dateList.add(new String[] { "nzwt326", "524142352714" });
		dateList.add(new String[] { "nzwt327", "524106215568" });
		dateList.add(new String[] { "nzwt328", "524257283605" });
		dateList.add(new String[] { "nzwt329", "524253455824" });
		dateList.add(new String[] { "nzwt330", "522022700874" });
		dateList.add(new String[] { "nzwt331", "522802977994" });
		dateList.add(new String[] { "nzwt332", "522809268240" });
		dateList.add(new String[] { "nzwt333", "522802882741" });
		dateList.add(new String[] { "nzwt334", "522803229403" });
		dateList.add(new String[] { "nzwt335", "522781907943" });
		dateList.add(new String[] { "nzlyq722", "521842531296" });
		dateList.add(new String[] { "nzlyq723", "521843056879" });
		dateList.add(new String[] { "nzlyq724", "521870418585" });
		dateList.add(new String[] { "nzlyq725", "521870089690" });
		dateList.add(new String[] { "nzlyq726", "521874158316" });
		dateList.add(new String[] { "nzlyq727", "521842121699" });
		dateList.add(new String[] { "nzlyq728", "521882520956" });
		dateList.add(new String[] { "nzlyq729", "521874174210" });
		dateList.add(new String[] { "nzlyq730", "521841789773" });
		dateList.add(new String[] { "nzlyq731", "521846922201" });
		dateList.add(new String[] { "nzlyq732", "521842421203" });
		dateList.add(new String[] { "nzlyq733", "521830855137" });
		dateList.add(new String[] { "nzlyq734", "522804582408" });
		dateList.add(new String[] { "nzlyq735", "522860708858" });
		dateList.add(new String[] { "nzlyq736", "522804709192" });
		dateList.add(new String[] { "nzlyq737", "522889272799" });
		dateList.add(new String[] { "nzlyq738", "522870369694" });
		dateList.add(new String[] { "nzlyq739", "522925513374" });
		dateList.add(new String[] { "nzlyq740", "522934630368" });
		dateList.add(new String[] { "nzlyq741", "522946085922" });
		dateList.add(new String[] { "nzlyq742", "523022049185" });
		dateList.add(new String[] { "nzlyq743", "522933806296" });
		dateList.add(new String[] { "nzlyq744", "523019242828" });
		dateList.add(new String[] { "nzlyq745", "522969611561" });
		dateList.add(new String[] { "nzlyq746", "522986165968" });
		dateList.add(new String[] { "nzlyq747", "523099774924" });
		dateList.add(new String[] { "nzlyq748", "523025940353" });
		dateList.add(new String[] { "nzlyq749", "522973256968" });
		dateList.add(new String[] { "nzlyq750", "523303662137" });
		dateList.add(new String[] { "nzlyq751", "523312400146" });
		dateList.add(new String[] { "nzlyq752", "523282471918" });
		dateList.add(new String[] { "nzlyq753", "523312280756" });
		dateList.add(new String[] { "nzlyq754", "523314380096" });
		dateList.add(new String[] { "nzlyq755", "523312464265" });
		dateList.add(new String[] { "nzlyq756", "523281967784" });
		dateList.add(new String[] { "nzlyq757", "523312456080" });
		dateList.add(new String[] { "nzlyq758", "523284095289" });
		dateList.add(new String[] { "nzlyq759", "523284243203" });
		dateList.add(new String[] { "nzbsq001", "524286172162" });
		dateList.add(new String[] { "nzck004", "524275785424" });
		dateList.add(new String[] { "nzctx207", "523344707139" });
		dateList.add(new String[] { "nzctx208", "523760891302" });
		dateList.add(new String[] { "nzctx209", "524275253794" });
		dateList.add(new String[] { "nzctx210", "524285916681" });
		dateList.add(new String[] { "nzctx211", "524247531983" });
		dateList.add(new String[] { "nzctx212", "524286828662" });
		dateList.add(new String[] { "nzctx213", "524318216615" });
		dateList.add(new String[] { "nzctx214", "524273095172" });
		dateList.add(new String[] { "nzddk135", "524246155917" });
		dateList.add(new String[] { "nzddk137", "524286236154" });
		dateList.add(new String[] { "nzks158", "523157183440" });
		dateList.add(new String[] { "nzks159", "524276709087" });
		dateList.add(new String[] { "nzmy093", "523053765858" });
		dateList.add(new String[] { "nzmy094", "522968370695" });
		dateList.add(new String[] { "nzmy095", "523034059023" });
		dateList.add(new String[] { "nzmy096", "523224756968" });
		dateList.add(new String[] { "nzmy097", "524285164918" });
		dateList.add(new String[] { "nzmy098", "524285216993" });
		dateList.add(new String[] { "nzmy099", "524277077020" });
		dateList.add(new String[] { "nzmy100", "524246959775" });
		dateList.add(new String[] { "nzmy101", "524275417964" });
		dateList.add(new String[] { "nzmy102", "524275322453" });
		dateList.add(new String[] { "nzmy103", "524286828256" });
		dateList.add(new String[] { "nzmy104", "524276641323" });
		dateList.add(new String[] { "nzmy105", "524286636563" });
		dateList.add(new String[] { "nzmy106", "524276129859" });
		dateList.add(new String[] { "nzmy107", "524276709553" });
		dateList.add(new String[] { "nzmy108", "524276385626" });
		dateList.add(new String[] { "nzmy109", "524274870794" });
		dateList.add(new String[] { "nzmy110", "524247551685" });
		dateList.add(new String[] { "nzmy111", "524247427750" });
		dateList.add(new String[] { "nzmy112", "524286828420" });
		dateList.add(new String[] { "nzmy113", "524248723292" });
		dateList.add(new String[] { "nzmy114", "524283042051" });
		dateList.add(new String[] { "nzmy115", "524282962207" });
		dateList.add(new String[] { "nzmy116", "524280714600" });
		dateList.add(new String[] { "nzmy117", "524275467138" });
		dateList.add(new String[] { "nzmy118", "524300442484" });
		dateList.add(new String[] { "nzmy119", "524272983971" });
		dateList.add(new String[] { "nzmy120", "524301382947" });
		dateList.add(new String[] { "nzmy121", "524329118240" });
		dateList.add(new String[] { "nztz168", "522912311717" });
		dateList.add(new String[] { "nztz169", "523031815497" });
		dateList.add(new String[] { "nztz170", "524281013210" });
		dateList.add(new String[] { "nztz171", "523747479047" });
		dateList.add(new String[] { "nztz172", "524275246484" });
		dateList.add(new String[] { "nztz173", "524274870631" });
		dateList.add(new String[] { "nztz174", "524285832939" });
		dateList.add(new String[] { "nztz175", "524275358551" });
		dateList.add(new String[] { "nztz176", "524286636524" });
		dateList.add(new String[] { "nztz177", "524247439726" });
		dateList.add(new String[] { "nztz178", "524277137471" });
		dateList.add(new String[] { "nztz179", "41925568572" });
		dateList.add(new String[] { "nztz180", "524308575421" });
		dateList.add(new String[] { "nzwy028", "524251823357" });
		dateList.add(new String[] { "nzwy030", "523307355089" });
		dateList.add(new String[] { "nzwy031", "523752930155" });
		dateList.add(new String[] { "nzwy032", "523785702083" });
		dateList.add(new String[] { "nzwy033", "523775194455" });
		dateList.add(new String[] { "nzwy034", "524275785595" });
		dateList.add(new String[] { "nzwy035", "524288836965" });
		dateList.add(new String[] { "nzwy036", "524248363072" });
		dateList.add(new String[] { "nzwy037", "524247427574" });
		dateList.add(new String[] { "nzwy038", "524286432421" });
		dateList.add(new String[] { "nzwy039", "524276641333" });
		dateList.add(new String[] { "nzwy040", "524276949153" });
		dateList.add(new String[] { "nzwy041", "524287044154" });
		dateList.add(new String[] { "nzwy042", "524274358968" });
		dateList.add(new String[] { "nzwy043", "524247439631" });
		dateList.add(new String[] { "nzwy044", "524287512050" });
		dateList.add(new String[] { "nzwy045", "524247439842" });
		dateList.add(new String[] { "nzwy046", "524286636700" });
		dateList.add(new String[] { "nzwy047", "524275962457" });
		dateList.add(new String[] { "nzwy048", "524248463438" });
		dateList.add(new String[] { "nzwy049", "522646156974" });
		dateList.add(new String[] { "nzwy050", "524268211311" });
		dateList.add(new String[] { "nzlyq810", "523084225287" });
		dateList.add(new String[] { "nzlyq811", "523139583435" });
		dateList.add(new String[] { "nzlyq812", "524249683469" });
		dateList.add(new String[] { "nzlyq813", "523335521039" });
		dateList.add(new String[] { "nzlyq814", "523289547329" });
		dateList.add(new String[] { "nzlyq815", "523329758780" });
		dateList.add(new String[] { "nzlyq816", "523366397010" });
		dateList.add(new String[] { "nzlyq817", "523752169989" });
		dateList.add(new String[] { "nzlyq818", "523784818153" });
		dateList.add(new String[] { "nzlyq819", "524275429673" });
		dateList.add(new String[] { "nzlyq820", "524247635089" });
		dateList.add(new String[] { "nzlyq821", "524247439158" });
		dateList.add(new String[] { "nzlyq822", "524275785595" });
		dateList.add(new String[] { "nzlyq823", "524286968053" });
		dateList.add(new String[] { "nzlyq824", "524286968052" });
		dateList.add(new String[] { "nzlyq825", "524285380899" });
		dateList.add(new String[] { "nzlyq826", "524275518235" });
		dateList.add(new String[] { "nzlyq827", "524285684807" });
		dateList.add(new String[] { "nzlyq828", "524286172569" });
		dateList.add(new String[] { "nzlyq829", "524286172562" });
		dateList.add(new String[] { "nzlyq830", "524248623040" });
		dateList.add(new String[] { "nzlyq831", "524277317150" });
		dateList.add(new String[] { "nzlyq832", "524247551775" });
		dateList.add(new String[] { "nzlyq833", "524275826525" });
		dateList.add(new String[] { "nzlyq834", "43530546224" });
		dateList.add(new String[] { "nzlyq835", "524307275575" });
		dateList.add(new String[] { "nzlyq836", "524338053610" });
		dateList.add(new String[] { "nzwt336", "522992663364" });
		dateList.add(new String[] { "nzwt337", "523083169798" });
		dateList.add(new String[] { "nzwt338", "523055957034" });
		dateList.add(new String[] { "nzwt340", "524277742978" });
		dateList.add(new String[] { "nzwt341", "523127230310" });
		dateList.add(new String[] { "nzwt342", "523130369032" });
		dateList.add(new String[] { "nzwt343", "523179722645" });
		dateList.add(new String[] { "nzwt344", "524278957203" });
		dateList.add(new String[] { "nzwt345", "523191054516" });
		dateList.add(new String[] { "nzwt346", "524281609057" });
		dateList.add(new String[] { "nzwt347", "523192318477" });
		dateList.add(new String[] { "nzwt348", "523220773126" });
		dateList.add(new String[] { "nzwt349", "523219363704" });
		dateList.add(new String[] { "nzwt350", "523197935278" });
		dateList.add(new String[] { "nzwt351", "523191074743" });
		dateList.add(new String[] { "nzwt352", "523313517371" });
		dateList.add(new String[] { "nzwt353", "42550919168" });
		dateList.add(new String[] { "nzwt354", "523724855220" });
		dateList.add(new String[] { "nzwt355", "523747450418" });
		dateList.add(new String[] { "nzwt356", "523751865997" });
		dateList.add(new String[] { "nzwt357", "523753119956" });
		dateList.add(new String[] { "nzwt358", "524276709046" });
		dateList.add(new String[] { "nzwt359", "524274878335" });
		dateList.add(new String[] { "nzwt360", "524247951063" });
		dateList.add(new String[] { "nzwt361", "524247427304" });
		dateList.add(new String[] { "nzwt362", "524276385207" });
		dateList.add(new String[] { "nzwt363", "524274786433" });
		dateList.add(new String[] { "nzwt364", "524275253838" });
		dateList.add(new String[] { "nzwt365", "524275518350" });
		dateList.add(new String[] { "nzwt366", "524286636327" });
		dateList.add(new String[] { "nzwt367", "524286432478" });
		dateList.add(new String[] { "nzwt368", "524285832817" });
		dateList.add(new String[] { "nzwt369", "524274786769" });
		dateList.add(new String[] { "nzwt370", "524286432629" });
		dateList.add(new String[] { "nzwt371", "524275322616" });
		dateList.add(new String[] { "nzwt372", "524248363313" });
		dateList.add(new String[] { "nzwt373", "524286172858" });
		dateList.add(new String[] { "nzwt374", "524286424665" });
		dateList.add(new String[] { "nzwt375", "524248363313" });
		dateList.add(new String[] { "nzwt376", "524247635865" });
		dateList.add(new String[] { "nzwt377", "524275246801" });
		dateList.add(new String[] { "nzwt378", "524247531983" });
		dateList.add(new String[] { "nzwt379", "524287048558" });
		dateList.add(new String[] { "nzwt380", "524334379192" });
		dateList.add(new String[] { "nzwt381", "524337694015" });
		dateList.add(new String[] { "nzddk138", "1205695056" });
		dateList.add(new String[] { "nzwt382", "1213382298" });
		dateList.add(new String[] { "nzwt383", "522796979793" });
		dateList.add(new String[] { "nzwt384", "522924178998" });
		dateList.add(new String[] { "nzwt385", "42708033476" });
		dateList.add(new String[] { "nzwt386", "522934340159" });
		dateList.add(new String[] { "nzwt387", "524140745275" });
		dateList.add(new String[] { "nzwt388", "524311277572" });
		dateList.add(new String[] { "nzwt389", "522983568160" });
		dateList.add(new String[] { "nzwt390", "40871165264" });

		for (String[] arr : dateList) {
			if (arr[1].equals(bango)) {
				result = arr[0];
				break;
			}
		}
		return result;
	}

}
