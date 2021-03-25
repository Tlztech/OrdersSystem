package com.rakuten.r1303.action;

import java.sql.Connection;
import java.sql.Statement;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1303.form.F130301;
import com.rakuten.util.JdbcConnection;

public class A13030102Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	private F130301 f130301 = null;

	protected void exec() throws Exception {
		Connection conn = null;
		Statement ps = null;
		try {
			conn = JdbcConnection.getConnection();
			ps = conn.createStatement();
			ps.addBatch("update soryo set kakaku ='" + f130301.getY101()
					+ "' where id = 'y101';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY102()
					+ "' where id = 'y102';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY103()
					+ "' where id = 'y103';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY104()
					+ "' where id = 'y104';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY105()
					+ "' where id = 'y105';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY106()
					+ "' where id = 'y106';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY107()
					+ "' where id = 'y107';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY108()
					+ "' where id = 'y108';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY109()
					+ "' where id = 'y109';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY110()
					+ "' where id = 'y110';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY111()
					+ "' where id = 'y111';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY112()
					+ "' where id = 'y112';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY113()
					+ "' where id = 'y113';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY114()
					+ "' where id = 'y114';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY115()
					+ "' where id = 'y115';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY116()
					+ "' where id = 'y116';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY117()
					+ "' where id = 'y117';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY118()
					+ "' where id = 'y118';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY119()
					+ "' where id = 'y119';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY120()
					+ "' where id = 'y120';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY121()
					+ "' where id = 'y121';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY122()
					+ "' where id = 'y122';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY123()
					+ "' where id = 'y123';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY124()
					+ "' where id = 'y124';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY125()
					+ "' where id = 'y125';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY126()
					+ "' where id = 'y126';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY127()
					+ "' where id = 'y127';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY128()
					+ "' where id = 'y128';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY129()
					+ "' where id = 'y129';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY130()
					+ "' where id = 'y130';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY131()
					+ "' where id = 'y131';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY132()
					+ "' where id = 'y132';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY133()
					+ "' where id = 'y133';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY134()
					+ "' where id = 'y134';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY135()
					+ "' where id = 'y135';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY136()
					+ "' where id = 'y136';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY137()
					+ "' where id = 'y137';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY138()
					+ "' where id = 'y138';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY139()
					+ "' where id = 'y139';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY140()
					+ "' where id = 'y140';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY141()
					+ "' where id = 'y141';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY142()
					+ "' where id = 'y142';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY143()
					+ "' where id = 'y143';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY144()
					+ "' where id = 'y144';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY145()
					+ "' where id = 'y145';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY146()
					+ "' where id = 'y146';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY147()
					+ "' where id = 'y147';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY148()
					+ "' where id = 'y148';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY201()
					+ "' where id = 'y201';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY202()
					+ "' where id = 'y202';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY203()
					+ "' where id = 'y203';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY204()
					+ "' where id = 'y204';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY205()
					+ "' where id = 'y205';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY206()
					+ "' where id = 'y206';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY207()
					+ "' where id = 'y207';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY208()
					+ "' where id = 'y208';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY209()
					+ "' where id = 'y209';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY210()
					+ "' where id = 'y210';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY211()
					+ "' where id = 'y211';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY212()
					+ "' where id = 'y212';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY213()
					+ "' where id = 'y213';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY214()
					+ "' where id = 'y214';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY215()
					+ "' where id = 'y215';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY216()
					+ "' where id = 'y216';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY217()
					+ "' where id = 'y217';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY218()
					+ "' where id = 'y218';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY219()
					+ "' where id = 'y219';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY220()
					+ "' where id = 'y220';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY221()
					+ "' where id = 'y221';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY222()
					+ "' where id = 'y222';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY223()
					+ "' where id = 'y223';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY224()
					+ "' where id = 'y224';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY225()
					+ "' where id = 'y225';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY226()
					+ "' where id = 'y226';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY227()
					+ "' where id = 'y227';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY228()
					+ "' where id = 'y228';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY229()
					+ "' where id = 'y229';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY230()
					+ "' where id = 'y230';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY231()
					+ "' where id = 'y231';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY232()
					+ "' where id = 'y232';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY233()
					+ "' where id = 'y233';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY234()
					+ "' where id = 'y234';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY235()
					+ "' where id = 'y235';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY236()
					+ "' where id = 'y236';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY237()
					+ "' where id = 'y237';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY238()
					+ "' where id = 'y238';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY239()
					+ "' where id = 'y239';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY240()
					+ "' where id = 'y240';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY241()
					+ "' where id = 'y241';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY242()
					+ "' where id = 'y242';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY243()
					+ "' where id = 'y243';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY244()
					+ "' where id = 'y244';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY245()
					+ "' where id = 'y245';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY246()
					+ "' where id = 'y246';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY247()
					+ "' where id = 'y247';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY248()
					+ "' where id = 'y248';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY301()
					+ "' where id = 'y301';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY302()
					+ "' where id = 'y302';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY303()
					+ "' where id = 'y303';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY304()
					+ "' where id = 'y304';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY305()
					+ "' where id = 'y305';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY306()
					+ "' where id = 'y306';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY307()
					+ "' where id = 'y307';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY308()
					+ "' where id = 'y308';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY309()
					+ "' where id = 'y309';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY310()
					+ "' where id = 'y310';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY311()
					+ "' where id = 'y311';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY312()
					+ "' where id = 'y312';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY313()
					+ "' where id = 'y313';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY314()
					+ "' where id = 'y314';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY315()
					+ "' where id = 'y315';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY316()
					+ "' where id = 'y316';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY317()
					+ "' where id = 'y317';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY318()
					+ "' where id = 'y318';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY319()
					+ "' where id = 'y319';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY320()
					+ "' where id = 'y320';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY321()
					+ "' where id = 'y321';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY322()
					+ "' where id = 'y322';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY323()
					+ "' where id = 'y323';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY324()
					+ "' where id = 'y324';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY325()
					+ "' where id = 'y325';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY326()
					+ "' where id = 'y326';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY327()
					+ "' where id = 'y327';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY328()
					+ "' where id = 'y328';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY329()
					+ "' where id = 'y329';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY330()
					+ "' where id = 'y330';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY331()
					+ "' where id = 'y331';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY332()
					+ "' where id = 'y332';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY333()
					+ "' where id = 'y333';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY334()
					+ "' where id = 'y334';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY335()
					+ "' where id = 'y335';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY336()
					+ "' where id = 'y336';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY337()
					+ "' where id = 'y337';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY338()
					+ "' where id = 'y338';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY339()
					+ "' where id = 'y339';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY340()
					+ "' where id = 'y340';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY341()
					+ "' where id = 'y341';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY342()
					+ "' where id = 'y342';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY343()
					+ "' where id = 'y343';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY344()
					+ "' where id = 'y344';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY345()
					+ "' where id = 'y345';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY346()
					+ "' where id = 'y346';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY347()
					+ "' where id = 'y347';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY348()
					+ "' where id = 'y348';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY401()
					+ "' where id = 'y401';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY402()
					+ "' where id = 'y402';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY403()
					+ "' where id = 'y403';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY404()
					+ "' where id = 'y404';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY405()
					+ "' where id = 'y405';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY406()
					+ "' where id = 'y406';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY407()
					+ "' where id = 'y407';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY408()
					+ "' where id = 'y408';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY409()
					+ "' where id = 'y409';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY410()
					+ "' where id = 'y410';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY411()
					+ "' where id = 'y411';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY412()
					+ "' where id = 'y412';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY413()
					+ "' where id = 'y413';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY414()
					+ "' where id = 'y414';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY415()
					+ "' where id = 'y415';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY416()
					+ "' where id = 'y416';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY417()
					+ "' where id = 'y417';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY418()
					+ "' where id = 'y418';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY419()
					+ "' where id = 'y419';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY420()
					+ "' where id = 'y420';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY421()
					+ "' where id = 'y421';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY422()
					+ "' where id = 'y422';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY423()
					+ "' where id = 'y423';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY424()
					+ "' where id = 'y424';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY425()
					+ "' where id = 'y425';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY426()
					+ "' where id = 'y426';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY427()
					+ "' where id = 'y427';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY428()
					+ "' where id = 'y428';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY429()
					+ "' where id = 'y429';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY430()
					+ "' where id = 'y430';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY431()
					+ "' where id = 'y431';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY432()
					+ "' where id = 'y432';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY433()
					+ "' where id = 'y433';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY434()
					+ "' where id = 'y434';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY435()
					+ "' where id = 'y435';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY436()
					+ "' where id = 'y436';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY437()
					+ "' where id = 'y437';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY438()
					+ "' where id = 'y438';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY439()
					+ "' where id = 'y439';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY440()
					+ "' where id = 'y440';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY441()
					+ "' where id = 'y441';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY442()
					+ "' where id = 'y442';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY443()
					+ "' where id = 'y443';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY444()
					+ "' where id = 'y444';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY445()
					+ "' where id = 'y445';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY446()
					+ "' where id = 'y446';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY447()
					+ "' where id = 'y447';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY448()
					+ "' where id = 'y448';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY501() +"' where id = 'y501';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY502() +"' where id = 'y502';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY503() +"' where id = 'y503';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY504() +"' where id = 'y504';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY505() +"' where id = 'y505';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY506() +"' where id = 'y506';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY507() +"' where id = 'y507';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY508() +"' where id = 'y508';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY509() +"' where id = 'y509';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY510() +"' where id = 'y510';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY511() +"' where id = 'y511';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY512() +"' where id = 'y512';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY513() +"' where id = 'y513';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY514() +"' where id = 'y514';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY515() +"' where id = 'y515';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY516() +"' where id = 'y516';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY517() +"' where id = 'y517';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY518() +"' where id = 'y518';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY519() +"' where id = 'y519';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY520() +"' where id = 'y520';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY521() +"' where id = 'y521';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY522() +"' where id = 'y522';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY523() +"' where id = 'y523';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY524() +"' where id = 'y524';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY525() +"' where id = 'y525';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY526() +"' where id = 'y526';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY527() +"' where id = 'y527';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY528() +"' where id = 'y528';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY529() +"' where id = 'y529';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY530() +"' where id = 'y530';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY531() +"' where id = 'y531';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY532() +"' where id = 'y532';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY533() +"' where id = 'y533';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY534() +"' where id = 'y534';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY535() +"' where id = 'y535';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY536() +"' where id = 'y536';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY537() +"' where id = 'y537';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY538() +"' where id = 'y538';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY539() +"' where id = 'y539';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY540() +"' where id = 'y540';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY541() +"' where id = 'y541';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY542() +"' where id = 'y542';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY543() +"' where id = 'y543';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY544() +"' where id = 'y544';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY545() +"' where id = 'y545';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY546() +"' where id = 'y546';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY547() +"' where id = 'y547';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY548() +"' where id = 'y548';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY601() +"' where id = 'y601';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY602() +"' where id = 'y602';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY603() +"' where id = 'y603';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY604() +"' where id = 'y604';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY605() +"' where id = 'y605';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY606() +"' where id = 'y606';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY607() +"' where id = 'y607';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY608() +"' where id = 'y608';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY609() +"' where id = 'y609';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY610() +"' where id = 'y610';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY611() +"' where id = 'y611';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY612() +"' where id = 'y612';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY613() +"' where id = 'y613';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY614() +"' where id = 'y614';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY615() +"' where id = 'y615';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY616() +"' where id = 'y616';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY617() +"' where id = 'y617';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY618() +"' where id = 'y618';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY619() +"' where id = 'y619';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY620() +"' where id = 'y620';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY621() +"' where id = 'y621';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY622() +"' where id = 'y622';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY623() +"' where id = 'y623';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY624() +"' where id = 'y624';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY625() +"' where id = 'y625';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY626() +"' where id = 'y626';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY627() +"' where id = 'y627';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY628() +"' where id = 'y628';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY629() +"' where id = 'y629';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY630() +"' where id = 'y630';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY631() +"' where id = 'y631';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY632() +"' where id = 'y632';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY633() +"' where id = 'y633';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY634() +"' where id = 'y634';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY635() +"' where id = 'y635';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY636() +"' where id = 'y636';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY637() +"' where id = 'y637';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY638() +"' where id = 'y638';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY639() +"' where id = 'y639';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY640() +"' where id = 'y640';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY641() +"' where id = 'y641';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY642() +"' where id = 'y642';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY643() +"' where id = 'y643';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY644() +"' where id = 'y644';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY645() +"' where id = 'y645';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY646() +"' where id = 'y646';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY647() +"' where id = 'y647';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY648() +"' where id = 'y648';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY701() +"' where id = 'y701';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY702() +"' where id = 'y702';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY703() +"' where id = 'y703';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY704() +"' where id = 'y704';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY705() +"' where id = 'y705';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY706() +"' where id = 'y706';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY707() +"' where id = 'y707';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY708() +"' where id = 'y708';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY709() +"' where id = 'y709';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY710() +"' where id = 'y710';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY711() +"' where id = 'y711';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY712() +"' where id = 'y712';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY713() +"' where id = 'y713';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY714() +"' where id = 'y714';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY715() +"' where id = 'y715';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY716() +"' where id = 'y716';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY717() +"' where id = 'y717';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY718() +"' where id = 'y718';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY719() +"' where id = 'y719';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY720() +"' where id = 'y720';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY721() +"' where id = 'y721';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY722() +"' where id = 'y722';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY723() +"' where id = 'y723';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY724() +"' where id = 'y724';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY725() +"' where id = 'y725';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY726() +"' where id = 'y726';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY727() +"' where id = 'y727';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY728() +"' where id = 'y728';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY729() +"' where id = 'y729';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY730() +"' where id = 'y730';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY731() +"' where id = 'y731';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY732() +"' where id = 'y732';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY733() +"' where id = 'y733';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY734() +"' where id = 'y734';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY735() +"' where id = 'y735';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY736() +"' where id = 'y736';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY737() +"' where id = 'y737';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY738() +"' where id = 'y738';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY739() +"' where id = 'y739';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY740() +"' where id = 'y740';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY741() +"' where id = 'y741';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY742() +"' where id = 'y742';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY743() +"' where id = 'y743';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY744() +"' where id = 'y744';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY745() +"' where id = 'y745';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY746() +"' where id = 'y746';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY747() +"' where id = 'y747';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY748() +"' where id = 'y748';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY801() +"' where id = 'y801';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY802() +"' where id = 'y802';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY803() +"' where id = 'y803';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY804() +"' where id = 'y804';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY805() +"' where id = 'y805';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY806() +"' where id = 'y806';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY807() +"' where id = 'y807';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY808() +"' where id = 'y808';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY809() +"' where id = 'y809';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY810() +"' where id = 'y810';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY811() +"' where id = 'y811';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY812() +"' where id = 'y812';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY813() +"' where id = 'y813';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY814() +"' where id = 'y814';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY815() +"' where id = 'y815';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY816() +"' where id = 'y816';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY817() +"' where id = 'y817';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY818() +"' where id = 'y818';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY819() +"' where id = 'y819';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY820() +"' where id = 'y820';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY821() +"' where id = 'y821';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY822() +"' where id = 'y822';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY823() +"' where id = 'y823';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY824() +"' where id = 'y824';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY825() +"' where id = 'y825';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY826() +"' where id = 'y826';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY827() +"' where id = 'y827';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY828() +"' where id = 'y828';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY829() +"' where id = 'y829';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY830() +"' where id = 'y830';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY831() +"' where id = 'y831';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY832() +"' where id = 'y832';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY833() +"' where id = 'y833';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY834() +"' where id = 'y834';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY835() +"' where id = 'y835';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY836() +"' where id = 'y836';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY837() +"' where id = 'y837';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY838() +"' where id = 'y838';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY839() +"' where id = 'y839';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY840() +"' where id = 'y840';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY841() +"' where id = 'y841';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY842() +"' where id = 'y842';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY843() +"' where id = 'y843';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY844() +"' where id = 'y844';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY845() +"' where id = 'y845';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY846() +"' where id = 'y846';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY847() +"' where id = 'y847';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY848() +"' where id = 'y848';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY901() +"' where id = 'y901';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY902() +"' where id = 'y902';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY903() +"' where id = 'y903';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY904() +"' where id = 'y904';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY905() +"' where id = 'y905';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY906() +"' where id = 'y906';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY907() +"' where id = 'y907';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY908() +"' where id = 'y908';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY909() +"' where id = 'y909';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY910() +"' where id = 'y910';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY911() +"' where id = 'y911';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY912() +"' where id = 'y912';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY913() +"' where id = 'y913';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY914() +"' where id = 'y914';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY915() +"' where id = 'y915';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY916() +"' where id = 'y916';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY917() +"' where id = 'y917';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY918() +"' where id = 'y918';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY919() +"' where id = 'y919';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY920() +"' where id = 'y920';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY921() +"' where id = 'y921';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY922() +"' where id = 'y922';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY923() +"' where id = 'y923';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY924() +"' where id = 'y924';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY925() +"' where id = 'y925';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY926() +"' where id = 'y926';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY927() +"' where id = 'y927';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY928() +"' where id = 'y928';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY929() +"' where id = 'y929';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY930() +"' where id = 'y930';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY931() +"' where id = 'y931';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY932() +"' where id = 'y932';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY933() +"' where id = 'y933';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY934() +"' where id = 'y934';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY935() +"' where id = 'y935';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY936() +"' where id = 'y936';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY937() +"' where id = 'y937';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY938() +"' where id = 'y938';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY939() +"' where id = 'y939';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY940() +"' where id = 'y940';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY941() +"' where id = 'y941';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY942() +"' where id = 'y942';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY943() +"' where id = 'y943';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY944() +"' where id = 'y944';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY945() +"' where id = 'y945';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY946() +"' where id = 'y946';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY947() +"' where id = 'y947';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY948() +"' where id = 'y948';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY1001() +"' where id = 'y1001';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY1002() +"' where id = 'y1002';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY1003() +"' where id = 'y1003';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY1004() +"' where id = 'y1004';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY1005() +"' where id = 'y1005';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY1006() +"' where id = 'y1006';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY1007() +"' where id = 'y1007';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY1008() +"' where id = 'y1008';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY1009() +"' where id = 'y1009';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY1010() +"' where id = 'y1010';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY1011() +"' where id = 'y1011';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY1012() +"' where id = 'y1012';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY1013() +"' where id = 'y1013';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY1014() +"' where id = 'y1014';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY1015() +"' where id = 'y1015';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY1016() +"' where id = 'y1016';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY1017() +"' where id = 'y1017';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY1018() +"' where id = 'y1018';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY1019() +"' where id = 'y1019';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY1020() +"' where id = 'y1020';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY1021() +"' where id = 'y1021';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY1022() +"' where id = 'y1022';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY1023() +"' where id = 'y1023';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY1024() +"' where id = 'y1024';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY1025() +"' where id = 'y1025';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY1026() +"' where id = 'y1026';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY1027() +"' where id = 'y1027';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY1028() +"' where id = 'y1028';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY1029() +"' where id = 'y1029';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY1030() +"' where id = 'y1030';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY1031() +"' where id = 'y1031';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY1032() +"' where id = 'y1032';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY1033() +"' where id = 'y1033';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY1034() +"' where id = 'y1034';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY1035() +"' where id = 'y1035';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY1036() +"' where id = 'y1036';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY1037() +"' where id = 'y1037';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY1038() +"' where id = 'y1038';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY1039() +"' where id = 'y1039';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY1040() +"' where id = 'y1040';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY1041() +"' where id = 'y1041';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY1042() +"' where id = 'y1042';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY1043() +"' where id = 'y1043';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY1044() +"' where id = 'y1044';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY1045() +"' where id = 'y1045';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY1046() +"' where id = 'y1046';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY1047() +"' where id = 'y1047';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getY1048() +"' where id = 'y1048';");

			ps.addBatch("update soryo set kakaku ='" + f130301.getS101()
					+ "' where id = 's101';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS102()
					+ "' where id = 's102';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS103()
					+ "' where id = 's103';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS104()
					+ "' where id = 's104';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS105()
					+ "' where id = 's105';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS106()
					+ "' where id = 's106';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS107()
					+ "' where id = 's107';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS108()
					+ "' where id = 's108';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS109()
					+ "' where id = 's109';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS110()
					+ "' where id = 's110';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS111()
					+ "' where id = 's111';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS112()
					+ "' where id = 's112';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS113()
					+ "' where id = 's113';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS114()
					+ "' where id = 's114';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS115()
					+ "' where id = 's115';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS116()
					+ "' where id = 's116';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS117()
					+ "' where id = 's117';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS118()
					+ "' where id = 's118';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS119()
					+ "' where id = 's119';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS120()
					+ "' where id = 's120';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS121()
					+ "' where id = 's121';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS122()
					+ "' where id = 's122';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS123()
					+ "' where id = 's123';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS124()
					+ "' where id = 's124';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS125()
					+ "' where id = 's125';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS126()
					+ "' where id = 's126';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS127()
					+ "' where id = 's127';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS128()
					+ "' where id = 's128';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS129()
					+ "' where id = 's129';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS130()
					+ "' where id = 's130';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS131()
					+ "' where id = 's131';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS132()
					+ "' where id = 's132';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS133()
					+ "' where id = 's133';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS134()
					+ "' where id = 's134';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS135()
					+ "' where id = 's135';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS136()
					+ "' where id = 's136';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS137()
					+ "' where id = 's137';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS138()
					+ "' where id = 's138';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS139()
					+ "' where id = 's139';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS140()
					+ "' where id = 's140';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS141()
					+ "' where id = 's141';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS142()
					+ "' where id = 's142';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS143()
					+ "' where id = 's143';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS144()
					+ "' where id = 's144';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS145()
					+ "' where id = 's145';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS146()
					+ "' where id = 's146';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS147()
					+ "' where id = 's147';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS148()
					+ "' where id = 's148';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS201()
					+ "' where id = 's201';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS202()
					+ "' where id = 's202';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS203()
					+ "' where id = 's203';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS204()
					+ "' where id = 's204';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS205()
					+ "' where id = 's205';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS206()
					+ "' where id = 's206';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS207()
					+ "' where id = 's207';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS208()
					+ "' where id = 's208';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS209()
					+ "' where id = 's209';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS210()
					+ "' where id = 's210';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS211()
					+ "' where id = 's211';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS212()
					+ "' where id = 's212';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS213()
					+ "' where id = 's213';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS214()
					+ "' where id = 's214';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS215()
					+ "' where id = 's215';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS216()
					+ "' where id = 's216';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS217()
					+ "' where id = 's217';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS218()
					+ "' where id = 's218';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS219()
					+ "' where id = 's219';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS220()
					+ "' where id = 's220';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS221()
					+ "' where id = 's221';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS222()
					+ "' where id = 's222';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS223()
					+ "' where id = 's223';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS224()
					+ "' where id = 's224';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS225()
					+ "' where id = 's225';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS226()
					+ "' where id = 's226';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS227()
					+ "' where id = 's227';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS228()
					+ "' where id = 's228';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS229()
					+ "' where id = 's229';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS230()
					+ "' where id = 's230';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS231()
					+ "' where id = 's231';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS232()
					+ "' where id = 's232';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS233()
					+ "' where id = 's233';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS234()
					+ "' where id = 's234';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS235()
					+ "' where id = 's235';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS236()
					+ "' where id = 's236';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS237()
					+ "' where id = 's237';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS238()
					+ "' where id = 's238';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS239()
					+ "' where id = 's239';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS240()
					+ "' where id = 's240';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS241()
					+ "' where id = 's241';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS242()
					+ "' where id = 's242';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS243()
					+ "' where id = 's243';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS244()
					+ "' where id = 's244';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS245()
					+ "' where id = 's245';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS246()
					+ "' where id = 's246';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS247()
					+ "' where id = 's247';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS248()
					+ "' where id = 's248';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS301()
					+ "' where id = 's301';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS302()
					+ "' where id = 's302';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS303()
					+ "' where id = 's303';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS304()
					+ "' where id = 's304';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS305()
					+ "' where id = 's305';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS306()
					+ "' where id = 's306';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS307()
					+ "' where id = 's307';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS308()
					+ "' where id = 's308';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS309()
					+ "' where id = 's309';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS310()
					+ "' where id = 's310';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS311()
					+ "' where id = 's311';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS312()
					+ "' where id = 's312';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS313()
					+ "' where id = 's313';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS314()
					+ "' where id = 's314';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS315()
					+ "' where id = 's315';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS316()
					+ "' where id = 's316';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS317()
					+ "' where id = 's317';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS318()
					+ "' where id = 's318';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS319()
					+ "' where id = 's319';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS320()
					+ "' where id = 's320';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS321()
					+ "' where id = 's321';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS322()
					+ "' where id = 's322';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS323()
					+ "' where id = 's323';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS324()
					+ "' where id = 's324';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS325()
					+ "' where id = 's325';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS326()
					+ "' where id = 's326';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS327()
					+ "' where id = 's327';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS328()
					+ "' where id = 's328';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS329()
					+ "' where id = 's329';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS330()
					+ "' where id = 's330';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS331()
					+ "' where id = 's331';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS332()
					+ "' where id = 's332';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS333()
					+ "' where id = 's333';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS334()
					+ "' where id = 's334';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS335()
					+ "' where id = 's335';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS336()
					+ "' where id = 's336';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS337()
					+ "' where id = 's337';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS338()
					+ "' where id = 's338';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS339()
					+ "' where id = 's339';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS340()
					+ "' where id = 's340';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS341()
					+ "' where id = 's341';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS342()
					+ "' where id = 's342';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS343()
					+ "' where id = 's343';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS344()
					+ "' where id = 's344';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS345()
					+ "' where id = 's345';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS346()
					+ "' where id = 's346';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS347()
					+ "' where id = 's347';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS348()
					+ "' where id = 's348';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS401()
					+ "' where id = 's401';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS402()
					+ "' where id = 's402';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS403()
					+ "' where id = 's403';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS404()
					+ "' where id = 's404';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS405()
					+ "' where id = 's405';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS406()
					+ "' where id = 's406';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS407()
					+ "' where id = 's407';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS408()
					+ "' where id = 's408';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS409()
					+ "' where id = 's409';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS410()
					+ "' where id = 's410';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS411()
					+ "' where id = 's411';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS412()
					+ "' where id = 's412';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS413()
					+ "' where id = 's413';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS414()
					+ "' where id = 's414';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS415()
					+ "' where id = 's415';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS416()
					+ "' where id = 's416';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS417()
					+ "' where id = 's417';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS418()
					+ "' where id = 's418';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS419()
					+ "' where id = 's419';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS420()
					+ "' where id = 's420';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS421()
					+ "' where id = 's421';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS422()
					+ "' where id = 's422';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS423()
					+ "' where id = 's423';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS424()
					+ "' where id = 's424';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS425()
					+ "' where id = 's425';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS426()
					+ "' where id = 's426';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS427()
					+ "' where id = 's427';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS428()
					+ "' where id = 's428';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS429()
					+ "' where id = 's429';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS430()
					+ "' where id = 's430';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS431()
					+ "' where id = 's431';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS432()
					+ "' where id = 's432';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS433()
					+ "' where id = 's433';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS434()
					+ "' where id = 's434';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS435()
					+ "' where id = 's435';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS436()
					+ "' where id = 's436';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS437()
					+ "' where id = 's437';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS438()
					+ "' where id = 's438';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS439()
					+ "' where id = 's439';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS440()
					+ "' where id = 's440';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS441()
					+ "' where id = 's441';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS442()
					+ "' where id = 's442';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS443()
					+ "' where id = 's443';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS444()
					+ "' where id = 's444';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS445()
					+ "' where id = 's445';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS446()
					+ "' where id = 's446';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS447()
					+ "' where id = 's447';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS448()
					+ "' where id = 's448';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS501() +"' where id = 's501';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS502() +"' where id = 's502';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS503() +"' where id = 's503';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS504() +"' where id = 's504';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS505() +"' where id = 's505';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS506() +"' where id = 's506';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS507() +"' where id = 's507';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS508() +"' where id = 's508';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS509() +"' where id = 's509';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS510() +"' where id = 's510';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS511() +"' where id = 's511';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS512() +"' where id = 's512';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS513() +"' where id = 's513';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS514() +"' where id = 's514';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS515() +"' where id = 's515';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS516() +"' where id = 's516';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS517() +"' where id = 's517';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS518() +"' where id = 's518';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS519() +"' where id = 's519';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS520() +"' where id = 's520';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS521() +"' where id = 's521';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS522() +"' where id = 's522';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS523() +"' where id = 's523';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS524() +"' where id = 's524';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS525() +"' where id = 's525';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS526() +"' where id = 's526';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS527() +"' where id = 's527';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS528() +"' where id = 's528';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS529() +"' where id = 's529';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS530() +"' where id = 's530';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS531() +"' where id = 's531';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS532() +"' where id = 's532';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS533() +"' where id = 's533';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS534() +"' where id = 's534';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS535() +"' where id = 's535';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS536() +"' where id = 's536';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS537() +"' where id = 's537';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS538() +"' where id = 's538';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS539() +"' where id = 's539';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS540() +"' where id = 's540';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS541() +"' where id = 's541';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS542() +"' where id = 's542';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS543() +"' where id = 's543';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS544() +"' where id = 's544';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS545() +"' where id = 's545';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS546() +"' where id = 's546';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS547() +"' where id = 's547';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS548() +"' where id = 's548';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS601() +"' where id = 's601';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS602() +"' where id = 's602';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS603() +"' where id = 's603';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS604() +"' where id = 's604';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS605() +"' where id = 's605';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS606() +"' where id = 's606';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS607() +"' where id = 's607';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS608() +"' where id = 's608';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS609() +"' where id = 's609';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS610() +"' where id = 's610';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS611() +"' where id = 's611';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS612() +"' where id = 's612';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS613() +"' where id = 's613';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS614() +"' where id = 's614';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS615() +"' where id = 's615';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS616() +"' where id = 's616';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS617() +"' where id = 's617';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS618() +"' where id = 's618';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS619() +"' where id = 's619';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS620() +"' where id = 's620';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS621() +"' where id = 's621';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS622() +"' where id = 's622';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS623() +"' where id = 's623';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS624() +"' where id = 's624';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS625() +"' where id = 's625';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS626() +"' where id = 's626';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS627() +"' where id = 's627';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS628() +"' where id = 's628';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS629() +"' where id = 's629';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS630() +"' where id = 's630';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS631() +"' where id = 's631';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS632() +"' where id = 's632';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS633() +"' where id = 's633';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS634() +"' where id = 's634';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS635() +"' where id = 's635';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS636() +"' where id = 's636';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS637() +"' where id = 's637';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS638() +"' where id = 's638';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS639() +"' where id = 's639';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS640() +"' where id = 's640';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS641() +"' where id = 's641';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS642() +"' where id = 's642';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS643() +"' where id = 's643';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS644() +"' where id = 's644';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS645() +"' where id = 's645';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS646() +"' where id = 's646';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS647() +"' where id = 's647';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS648() +"' where id = 's648';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS701() +"' where id = 's701';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS702() +"' where id = 's702';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS703() +"' where id = 's703';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS704() +"' where id = 's704';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS705() +"' where id = 's705';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS706() +"' where id = 's706';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS707() +"' where id = 's707';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS708() +"' where id = 's708';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS709() +"' where id = 's709';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS710() +"' where id = 's710';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS711() +"' where id = 's711';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS712() +"' where id = 's712';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS713() +"' where id = 's713';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS714() +"' where id = 's714';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS715() +"' where id = 's715';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS716() +"' where id = 's716';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS717() +"' where id = 's717';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS718() +"' where id = 's718';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS719() +"' where id = 's719';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS720() +"' where id = 's720';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS721() +"' where id = 's721';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS722() +"' where id = 's722';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS723() +"' where id = 's723';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS724() +"' where id = 's724';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS725() +"' where id = 's725';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS726() +"' where id = 's726';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS727() +"' where id = 's727';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS728() +"' where id = 's728';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS729() +"' where id = 's729';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS730() +"' where id = 's730';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS731() +"' where id = 's731';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS732() +"' where id = 's732';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS733() +"' where id = 's733';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS734() +"' where id = 's734';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS735() +"' where id = 's735';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS736() +"' where id = 's736';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS737() +"' where id = 's737';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS738() +"' where id = 's738';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS739() +"' where id = 's739';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS740() +"' where id = 's740';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS741() +"' where id = 's741';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS742() +"' where id = 's742';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS743() +"' where id = 's743';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS744() +"' where id = 's744';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS745() +"' where id = 's745';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS746() +"' where id = 's746';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS747() +"' where id = 's747';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS748() +"' where id = 's748';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS801() +"' where id = 's801';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS802() +"' where id = 's802';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS803() +"' where id = 's803';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS804() +"' where id = 's804';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS805() +"' where id = 's805';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS806() +"' where id = 's806';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS807() +"' where id = 's807';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS808() +"' where id = 's808';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS809() +"' where id = 's809';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS810() +"' where id = 's810';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS811() +"' where id = 's811';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS812() +"' where id = 's812';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS813() +"' where id = 's813';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS814() +"' where id = 's814';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS815() +"' where id = 's815';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS816() +"' where id = 's816';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS817() +"' where id = 's817';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS818() +"' where id = 's818';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS819() +"' where id = 's819';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS820() +"' where id = 's820';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS821() +"' where id = 's821';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS822() +"' where id = 's822';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS823() +"' where id = 's823';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS824() +"' where id = 's824';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS825() +"' where id = 's825';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS826() +"' where id = 's826';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS827() +"' where id = 's827';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS828() +"' where id = 's828';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS829() +"' where id = 's829';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS830() +"' where id = 's830';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS831() +"' where id = 's831';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS832() +"' where id = 's832';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS833() +"' where id = 's833';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS834() +"' where id = 's834';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS835() +"' where id = 's835';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS836() +"' where id = 's836';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS837() +"' where id = 's837';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS838() +"' where id = 's838';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS839() +"' where id = 's839';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS840() +"' where id = 's840';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS841() +"' where id = 's841';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS842() +"' where id = 's842';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS843() +"' where id = 's843';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS844() +"' where id = 's844';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS845() +"' where id = 's845';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS846() +"' where id = 's846';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS847() +"' where id = 's847';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS848() +"' where id = 's848';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS901() +"' where id = 's901';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS902() +"' where id = 's902';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS903() +"' where id = 's903';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS904() +"' where id = 's904';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS905() +"' where id = 's905';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS906() +"' where id = 's906';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS907() +"' where id = 's907';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS908() +"' where id = 's908';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS909() +"' where id = 's909';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS910() +"' where id = 's910';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS911() +"' where id = 's911';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS912() +"' where id = 's912';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS913() +"' where id = 's913';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS914() +"' where id = 's914';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS915() +"' where id = 's915';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS916() +"' where id = 's916';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS917() +"' where id = 's917';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS918() +"' where id = 's918';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS919() +"' where id = 's919';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS920() +"' where id = 's920';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS921() +"' where id = 's921';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS922() +"' where id = 's922';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS923() +"' where id = 's923';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS924() +"' where id = 's924';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS925() +"' where id = 's925';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS926() +"' where id = 's926';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS927() +"' where id = 's927';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS928() +"' where id = 's928';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS929() +"' where id = 's929';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS930() +"' where id = 's930';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS931() +"' where id = 's931';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS932() +"' where id = 's932';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS933() +"' where id = 's933';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS934() +"' where id = 's934';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS935() +"' where id = 's935';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS936() +"' where id = 's936';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS937() +"' where id = 's937';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS938() +"' where id = 's938';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS939() +"' where id = 's939';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS940() +"' where id = 's940';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS941() +"' where id = 's941';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS942() +"' where id = 's942';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS943() +"' where id = 's943';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS944() +"' where id = 's944';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS945() +"' where id = 's945';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS946() +"' where id = 's946';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS947() +"' where id = 's947';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS948() +"' where id = 's948';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS1001() +"' where id = 's1001';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS1002() +"' where id = 's1002';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS1003() +"' where id = 's1003';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS1004() +"' where id = 's1004';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS1005() +"' where id = 's1005';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS1006() +"' where id = 's1006';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS1007() +"' where id = 's1007';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS1008() +"' where id = 's1008';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS1009() +"' where id = 's1009';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS1010() +"' where id = 's1010';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS1011() +"' where id = 's1011';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS1012() +"' where id = 's1012';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS1013() +"' where id = 's1013';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS1014() +"' where id = 's1014';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS1015() +"' where id = 's1015';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS1016() +"' where id = 's1016';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS1017() +"' where id = 's1017';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS1018() +"' where id = 's1018';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS1019() +"' where id = 's1019';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS1020() +"' where id = 's1020';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS1021() +"' where id = 's1021';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS1022() +"' where id = 's1022';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS1023() +"' where id = 's1023';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS1024() +"' where id = 's1024';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS1025() +"' where id = 's1025';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS1026() +"' where id = 's1026';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS1027() +"' where id = 's1027';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS1028() +"' where id = 's1028';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS1029() +"' where id = 's1029';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS1030() +"' where id = 's1030';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS1031() +"' where id = 's1031';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS1032() +"' where id = 's1032';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS1033() +"' where id = 's1033';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS1034() +"' where id = 's1034';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS1035() +"' where id = 's1035';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS1036() +"' where id = 's1036';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS1037() +"' where id = 's1037';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS1038() +"' where id = 's1038';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS1039() +"' where id = 's1039';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS1040() +"' where id = 's1040';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS1041() +"' where id = 's1041';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS1042() +"' where id = 's1042';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS1043() +"' where id = 's1043';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS1044() +"' where id = 's1044';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS1045() +"' where id = 's1045';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS1046() +"' where id = 's1046';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS1047() +"' where id = 's1047';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getS1048() +"' where id = 's1048';");
			
			ps.addBatch("update soryo set kakaku ='" + f130301.getP101() +"' where id = 'p101';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP102() +"' where id = 'p102';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP103() +"' where id = 'p103';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP104() +"' where id = 'p104';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP105() +"' where id = 'p105';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP106() +"' where id = 'p106';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP107() +"' where id = 'p107';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP108() +"' where id = 'p108';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP109() +"' where id = 'p109';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP110() +"' where id = 'p110';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP111() +"' where id = 'p111';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP112() +"' where id = 'p112';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP113() +"' where id = 'p113';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP114() +"' where id = 'p114';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP115() +"' where id = 'p115';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP116() +"' where id = 'p116';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP117() +"' where id = 'p117';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP118() +"' where id = 'p118';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP119() +"' where id = 'p119';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP120() +"' where id = 'p120';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP121() +"' where id = 'p121';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP122() +"' where id = 'p122';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP123() +"' where id = 'p123';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP124() +"' where id = 'p124';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP125() +"' where id = 'p125';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP126() +"' where id = 'p126';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP127() +"' where id = 'p127';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP128() +"' where id = 'p128';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP129() +"' where id = 'p129';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP130() +"' where id = 'p130';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP131() +"' where id = 'p131';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP132() +"' where id = 'p132';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP133() +"' where id = 'p133';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP134() +"' where id = 'p134';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP135() +"' where id = 'p135';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP136() +"' where id = 'p136';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP137() +"' where id = 'p137';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP138() +"' where id = 'p138';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP139() +"' where id = 'p139';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP140() +"' where id = 'p140';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP141() +"' where id = 'p141';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP142() +"' where id = 'p142';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP143() +"' where id = 'p143';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP144() +"' where id = 'p144';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP145() +"' where id = 'p145';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP146() +"' where id = 'p146';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP147() +"' where id = 'p147';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP148() +"' where id = 'p148';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP201() +"' where id = 'p201';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP202() +"' where id = 'p202';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP203() +"' where id = 'p203';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP204() +"' where id = 'p204';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP205() +"' where id = 'p205';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP206() +"' where id = 'p206';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP207() +"' where id = 'p207';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP208() +"' where id = 'p208';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP209() +"' where id = 'p209';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP210() +"' where id = 'p210';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP211() +"' where id = 'p211';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP212() +"' where id = 'p212';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP213() +"' where id = 'p213';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP214() +"' where id = 'p214';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP215() +"' where id = 'p215';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP216() +"' where id = 'p216';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP217() +"' where id = 'p217';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP218() +"' where id = 'p218';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP219() +"' where id = 'p219';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP220() +"' where id = 'p220';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP221() +"' where id = 'p221';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP222() +"' where id = 'p222';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP223() +"' where id = 'p223';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP224() +"' where id = 'p224';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP225() +"' where id = 'p225';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP226() +"' where id = 'p226';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP227() +"' where id = 'p227';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP228() +"' where id = 'p228';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP229() +"' where id = 'p229';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP230() +"' where id = 'p230';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP231() +"' where id = 'p231';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP232() +"' where id = 'p232';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP233() +"' where id = 'p233';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP234() +"' where id = 'p234';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP235() +"' where id = 'p235';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP236() +"' where id = 'p236';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP237() +"' where id = 'p237';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP238() +"' where id = 'p238';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP239() +"' where id = 'p239';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP240() +"' where id = 'p240';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP241() +"' where id = 'p241';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP242() +"' where id = 'p242';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP243() +"' where id = 'p243';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP244() +"' where id = 'p244';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP245() +"' where id = 'p245';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP246() +"' where id = 'p246';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP247() +"' where id = 'p247';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP248() +"' where id = 'p248';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP301() +"' where id = 'p301';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP302() +"' where id = 'p302';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP303() +"' where id = 'p303';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP304() +"' where id = 'p304';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP305() +"' where id = 'p305';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP306() +"' where id = 'p306';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP307() +"' where id = 'p307';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP308() +"' where id = 'p308';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP309() +"' where id = 'p309';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP310() +"' where id = 'p310';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP311() +"' where id = 'p311';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP312() +"' where id = 'p312';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP313() +"' where id = 'p313';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP314() +"' where id = 'p314';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP315() +"' where id = 'p315';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP316() +"' where id = 'p316';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP317() +"' where id = 'p317';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP318() +"' where id = 'p318';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP319() +"' where id = 'p319';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP320() +"' where id = 'p320';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP321() +"' where id = 'p321';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP322() +"' where id = 'p322';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP323() +"' where id = 'p323';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP324() +"' where id = 'p324';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP325() +"' where id = 'p325';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP326() +"' where id = 'p326';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP327() +"' where id = 'p327';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP328() +"' where id = 'p328';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP329() +"' where id = 'p329';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP330() +"' where id = 'p330';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP331() +"' where id = 'p331';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP332() +"' where id = 'p332';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP333() +"' where id = 'p333';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP334() +"' where id = 'p334';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP335() +"' where id = 'p335';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP336() +"' where id = 'p336';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP337() +"' where id = 'p337';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP338() +"' where id = 'p338';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP339() +"' where id = 'p339';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP340() +"' where id = 'p340';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP341() +"' where id = 'p341';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP342() +"' where id = 'p342';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP343() +"' where id = 'p343';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP344() +"' where id = 'p344';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP345() +"' where id = 'p345';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP346() +"' where id = 'p346';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP347() +"' where id = 'p347';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP348() +"' where id = 'p348';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP401() +"' where id = 'p401';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP402() +"' where id = 'p402';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP403() +"' where id = 'p403';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP404() +"' where id = 'p404';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP405() +"' where id = 'p405';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP406() +"' where id = 'p406';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP407() +"' where id = 'p407';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP408() +"' where id = 'p408';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP409() +"' where id = 'p409';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP410() +"' where id = 'p410';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP411() +"' where id = 'p411';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP412() +"' where id = 'p412';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP413() +"' where id = 'p413';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP414() +"' where id = 'p414';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP415() +"' where id = 'p415';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP416() +"' where id = 'p416';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP417() +"' where id = 'p417';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP418() +"' where id = 'p418';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP419() +"' where id = 'p419';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP420() +"' where id = 'p420';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP421() +"' where id = 'p421';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP422() +"' where id = 'p422';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP423() +"' where id = 'p423';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP424() +"' where id = 'p424';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP425() +"' where id = 'p425';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP426() +"' where id = 'p426';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP427() +"' where id = 'p427';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP428() +"' where id = 'p428';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP429() +"' where id = 'p429';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP430() +"' where id = 'p430';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP431() +"' where id = 'p431';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP432() +"' where id = 'p432';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP433() +"' where id = 'p433';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP434() +"' where id = 'p434';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP435() +"' where id = 'p435';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP436() +"' where id = 'p436';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP437() +"' where id = 'p437';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP438() +"' where id = 'p438';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP439() +"' where id = 'p439';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP440() +"' where id = 'p440';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP441() +"' where id = 'p441';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP442() +"' where id = 'p442';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP443() +"' where id = 'p443';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP444() +"' where id = 'p444';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP445() +"' where id = 'p445';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP446() +"' where id = 'p446';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP447() +"' where id = 'p447';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP448() +"' where id = 'p448';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP501() +"' where id = 'p501';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP502() +"' where id = 'p502';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP503() +"' where id = 'p503';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP504() +"' where id = 'p504';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP505() +"' where id = 'p505';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP506() +"' where id = 'p506';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP507() +"' where id = 'p507';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP508() +"' where id = 'p508';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP509() +"' where id = 'p509';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP510() +"' where id = 'p510';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP511() +"' where id = 'p511';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP512() +"' where id = 'p512';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP513() +"' where id = 'p513';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP514() +"' where id = 'p514';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP515() +"' where id = 'p515';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP516() +"' where id = 'p516';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP517() +"' where id = 'p517';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP518() +"' where id = 'p518';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP519() +"' where id = 'p519';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP520() +"' where id = 'p520';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP521() +"' where id = 'p521';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP522() +"' where id = 'p522';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP523() +"' where id = 'p523';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP524() +"' where id = 'p524';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP525() +"' where id = 'p525';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP526() +"' where id = 'p526';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP527() +"' where id = 'p527';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP528() +"' where id = 'p528';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP529() +"' where id = 'p529';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP530() +"' where id = 'p530';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP531() +"' where id = 'p531';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP532() +"' where id = 'p532';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP533() +"' where id = 'p533';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP534() +"' where id = 'p534';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP535() +"' where id = 'p535';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP536() +"' where id = 'p536';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP537() +"' where id = 'p537';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP538() +"' where id = 'p538';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP539() +"' where id = 'p539';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP540() +"' where id = 'p540';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP541() +"' where id = 'p541';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP542() +"' where id = 'p542';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP543() +"' where id = 'p543';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP544() +"' where id = 'p544';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP545() +"' where id = 'p545';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP546() +"' where id = 'p546';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP547() +"' where id = 'p547';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP548() +"' where id = 'p548';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP601() +"' where id = 'p601';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP602() +"' where id = 'p602';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP603() +"' where id = 'p603';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP604() +"' where id = 'p604';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP605() +"' where id = 'p605';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP606() +"' where id = 'p606';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP607() +"' where id = 'p607';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP608() +"' where id = 'p608';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP609() +"' where id = 'p609';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP610() +"' where id = 'p610';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP611() +"' where id = 'p611';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP612() +"' where id = 'p612';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP613() +"' where id = 'p613';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP614() +"' where id = 'p614';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP615() +"' where id = 'p615';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP616() +"' where id = 'p616';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP617() +"' where id = 'p617';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP618() +"' where id = 'p618';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP619() +"' where id = 'p619';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP620() +"' where id = 'p620';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP621() +"' where id = 'p621';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP622() +"' where id = 'p622';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP623() +"' where id = 'p623';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP624() +"' where id = 'p624';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP625() +"' where id = 'p625';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP626() +"' where id = 'p626';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP627() +"' where id = 'p627';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP628() +"' where id = 'p628';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP629() +"' where id = 'p629';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP630() +"' where id = 'p630';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP631() +"' where id = 'p631';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP632() +"' where id = 'p632';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP633() +"' where id = 'p633';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP634() +"' where id = 'p634';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP635() +"' where id = 'p635';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP636() +"' where id = 'p636';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP637() +"' where id = 'p637';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP638() +"' where id = 'p638';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP639() +"' where id = 'p639';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP640() +"' where id = 'p640';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP641() +"' where id = 'p641';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP642() +"' where id = 'p642';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP643() +"' where id = 'p643';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP644() +"' where id = 'p644';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP645() +"' where id = 'p645';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP646() +"' where id = 'p646';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP647() +"' where id = 'p647';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP648() +"' where id = 'p648';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP701() +"' where id = 'p701';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP702() +"' where id = 'p702';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP703() +"' where id = 'p703';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP704() +"' where id = 'p704';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP705() +"' where id = 'p705';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP706() +"' where id = 'p706';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP707() +"' where id = 'p707';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP708() +"' where id = 'p708';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP709() +"' where id = 'p709';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP710() +"' where id = 'p710';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP711() +"' where id = 'p711';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP712() +"' where id = 'p712';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP713() +"' where id = 'p713';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP714() +"' where id = 'p714';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP715() +"' where id = 'p715';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP716() +"' where id = 'p716';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP717() +"' where id = 'p717';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP718() +"' where id = 'p718';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP719() +"' where id = 'p719';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP720() +"' where id = 'p720';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP721() +"' where id = 'p721';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP722() +"' where id = 'p722';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP723() +"' where id = 'p723';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP724() +"' where id = 'p724';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP725() +"' where id = 'p725';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP726() +"' where id = 'p726';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP727() +"' where id = 'p727';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP728() +"' where id = 'p728';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP729() +"' where id = 'p729';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP730() +"' where id = 'p730';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP731() +"' where id = 'p731';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP732() +"' where id = 'p732';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP733() +"' where id = 'p733';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP734() +"' where id = 'p734';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP735() +"' where id = 'p735';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP736() +"' where id = 'p736';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP737() +"' where id = 'p737';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP738() +"' where id = 'p738';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP739() +"' where id = 'p739';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP740() +"' where id = 'p740';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP741() +"' where id = 'p741';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP742() +"' where id = 'p742';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP743() +"' where id = 'p743';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP744() +"' where id = 'p744';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP745() +"' where id = 'p745';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP746() +"' where id = 'p746';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP747() +"' where id = 'p747';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP748() +"' where id = 'p748';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP801() +"' where id = 'p801';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP802() +"' where id = 'p802';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP803() +"' where id = 'p803';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP804() +"' where id = 'p804';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP805() +"' where id = 'p805';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP806() +"' where id = 'p806';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP807() +"' where id = 'p807';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP808() +"' where id = 'p808';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP809() +"' where id = 'p809';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP810() +"' where id = 'p810';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP811() +"' where id = 'p811';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP812() +"' where id = 'p812';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP813() +"' where id = 'p813';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP814() +"' where id = 'p814';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP815() +"' where id = 'p815';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP816() +"' where id = 'p816';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP817() +"' where id = 'p817';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP818() +"' where id = 'p818';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP819() +"' where id = 'p819';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP820() +"' where id = 'p820';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP821() +"' where id = 'p821';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP822() +"' where id = 'p822';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP823() +"' where id = 'p823';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP824() +"' where id = 'p824';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP825() +"' where id = 'p825';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP826() +"' where id = 'p826';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP827() +"' where id = 'p827';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP828() +"' where id = 'p828';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP829() +"' where id = 'p829';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP830() +"' where id = 'p830';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP831() +"' where id = 'p831';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP832() +"' where id = 'p832';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP833() +"' where id = 'p833';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP834() +"' where id = 'p834';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP835() +"' where id = 'p835';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP836() +"' where id = 'p836';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP837() +"' where id = 'p837';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP838() +"' where id = 'p838';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP839() +"' where id = 'p839';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP840() +"' where id = 'p840';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP841() +"' where id = 'p841';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP842() +"' where id = 'p842';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP843() +"' where id = 'p843';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP844() +"' where id = 'p844';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP845() +"' where id = 'p845';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP846() +"' where id = 'p846';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP847() +"' where id = 'p847';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP848() +"' where id = 'p848';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP901() +"' where id = 'p901';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP902() +"' where id = 'p902';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP903() +"' where id = 'p903';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP904() +"' where id = 'p904';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP905() +"' where id = 'p905';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP906() +"' where id = 'p906';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP907() +"' where id = 'p907';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP908() +"' where id = 'p908';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP909() +"' where id = 'p909';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP910() +"' where id = 'p910';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP911() +"' where id = 'p911';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP912() +"' where id = 'p912';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP913() +"' where id = 'p913';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP914() +"' where id = 'p914';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP915() +"' where id = 'p915';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP916() +"' where id = 'p916';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP917() +"' where id = 'p917';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP918() +"' where id = 'p918';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP919() +"' where id = 'p919';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP920() +"' where id = 'p920';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP921() +"' where id = 'p921';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP922() +"' where id = 'p922';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP923() +"' where id = 'p923';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP924() +"' where id = 'p924';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP925() +"' where id = 'p925';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP926() +"' where id = 'p926';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP927() +"' where id = 'p927';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP928() +"' where id = 'p928';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP929() +"' where id = 'p929';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP930() +"' where id = 'p930';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP931() +"' where id = 'p931';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP932() +"' where id = 'p932';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP933() +"' where id = 'p933';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP934() +"' where id = 'p934';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP935() +"' where id = 'p935';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP936() +"' where id = 'p936';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP937() +"' where id = 'p937';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP938() +"' where id = 'p938';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP939() +"' where id = 'p939';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP940() +"' where id = 'p940';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP941() +"' where id = 'p941';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP942() +"' where id = 'p942';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP943() +"' where id = 'p943';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP944() +"' where id = 'p944';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP945() +"' where id = 'p945';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP946() +"' where id = 'p946';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP947() +"' where id = 'p947';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP948() +"' where id = 'p948';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP1001() +"' where id = 'p1001';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP1002() +"' where id = 'p1002';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP1003() +"' where id = 'p1003';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP1004() +"' where id = 'p1004';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP1005() +"' where id = 'p1005';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP1006() +"' where id = 'p1006';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP1007() +"' where id = 'p1007';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP1008() +"' where id = 'p1008';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP1009() +"' where id = 'p1009';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP1010() +"' where id = 'p1010';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP1011() +"' where id = 'p1011';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP1012() +"' where id = 'p1012';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP1013() +"' where id = 'p1013';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP1014() +"' where id = 'p1014';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP1015() +"' where id = 'p1015';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP1016() +"' where id = 'p1016';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP1017() +"' where id = 'p1017';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP1018() +"' where id = 'p1018';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP1019() +"' where id = 'p1019';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP1020() +"' where id = 'p1020';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP1021() +"' where id = 'p1021';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP1022() +"' where id = 'p1022';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP1023() +"' where id = 'p1023';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP1024() +"' where id = 'p1024';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP1025() +"' where id = 'p1025';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP1026() +"' where id = 'p1026';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP1027() +"' where id = 'p1027';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP1028() +"' where id = 'p1028';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP1029() +"' where id = 'p1029';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP1030() +"' where id = 'p1030';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP1031() +"' where id = 'p1031';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP1032() +"' where id = 'p1032';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP1033() +"' where id = 'p1033';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP1034() +"' where id = 'p1034';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP1035() +"' where id = 'p1035';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP1036() +"' where id = 'p1036';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP1037() +"' where id = 'p1037';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP1038() +"' where id = 'p1038';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP1039() +"' where id = 'p1039';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP1040() +"' where id = 'p1040';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP1041() +"' where id = 'p1041';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP1042() +"' where id = 'p1042';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP1043() +"' where id = 'p1043';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP1044() +"' where id = 'p1044';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP1045() +"' where id = 'p1045';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP1046() +"' where id = 'p1046';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP1047() +"' where id = 'p1047';");
			ps.addBatch("update soryo set kakaku ='" + f130301.getP1048() +"' where id = 'p1048';");

			ps.executeBatch();
			conn.commit();
			addError(null, "成功しました！");
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}

	protected void init() throws Exception {
		setTitle("V130301:送料設定");
	}

	protected void isValidated() throws Exception {

	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	public F130301 getF130301() {
		return f130301;
	}

	public void setF130301(F130301 f130301) {
		this.f130301 = f130301;
	}

}
