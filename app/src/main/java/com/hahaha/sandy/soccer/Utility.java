package com.hahaha.sandy.soccer;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.Log;

import java.lang.ref.SoftReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hahaha on 15/01/16.
 */
public class Utility {
    public static String scoreString(Context context,int goalHome,int goalAway)
    {
        return String.format(context.getString(R.string.score_string),goalHome,goalAway);
    }
    public static String getMatchString(Context context,int day)
    {
        return String.format(context.getString(R.string.current_match),day);
    }
    public static String getTotalTeams(Context context,int teams)
    {
        return String.format(context.getString(R.string.number_teams),teams);
    }
    public static String getTotalGames(Context context,int games)
    {
        return String.format(context.getString(R.string.number_games),games);
    }
    public static String convertDate(Context context,String dateString)
    {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd MMM,yy");
            Date date = simpleDateFormat.parse(dateString);
            return simpleDateFormat1.format(date);
        }catch (Exception e)
        {

        }
        return null;
    }
    public static String ConvertTime(Context context,String timeStamp)
    {       try {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("hh:mm aa");
        return simpleDateFormat1.format(simpleDateFormat.parse(timeStamp));
    }catch (Exception e)
    {

    }
        return null;
    }
    public static String time_date(Context context,String day,String timeStamp)
    {
        String dayString = convertDate(context,day);
        String tame = ConvertTime(context,timeStamp);
        return String.format(context.getString(R.string.time_date),dayString,tame);
    }
    public static int getImage(Context context,int id)
    {
        if(id == 394 || id == 395)
            return R.drawable.bun;
        else if(id == 396)
            return R.drawable.lige;
        else if(id == 397)
            return R.drawable.lig;
        else if(id == 398)
            return R.drawable.per;
        else if(id == 399)
            return R.drawable.pri;
        else if(id == 400)
            return R.drawable.seg;
        else if(id == 401)
            return R.drawable.ser;
        else if(id == 402)
            return R.drawable.perm;
        else if(id == 403)
            return R.drawable.bun;
        else if(id == 404)
            return R.drawable.ere;
        else if(id == 405)
            return R.drawable.cham;
        return android.R.drawable.ic_dialog_alert;
    }
    public static int getTeamsImage(Context context,String count)
    {
        if(count.equals("5"))
            return R.drawable.bayermun;
        else if(count.equals("7"))
            return R.drawable.hamburger;
        else if(count.equals("16"))
            return R.drawable.augsburg;
        else if(count.equals("9"))
            return R.drawable.herthabsc;
        else if(count.equals("3"))
            return R.drawable.bayerleverkusen;
        else if(count.equals("2"))
            return R.drawable.tsg1899hoffenheim;
        else if(count.equals("55"))
            return R.drawable.darmstadt;
        else if(count.equals("8"))
            return R.drawable.hannover;
        else if(count.equals("15"))
            return R.drawable.fsvmainz;
        else if(count.equals("31"))
            return R.drawable.ingolstadt;
        else if(count.equals("12"))
            return R.drawable.werderbremen;
        else if(count.equals("6"))
            return R.drawable.schalke;
        else if(count.equals("4"))
            return R.drawable.bordortmund;
        else if(count.equals("18"))
            return R.drawable.monchenglabach;
        else if(count.equals("11"))
            return R.drawable.vflwolfsburg;
        else if(count.equals("19"))
            return R.drawable.eintrachtfrankfurt;
        else if(count.equals("10"))
            return R.drawable.vfbstuttgart;
        else if(count.equals("1"))
            return R.drawable.koln;
        else if(count.equals("25"))
            return R.drawable.msvduisburg;
        else if(count.equals("13"))
            return R.drawable.kaiserlautern;
        else if(count.equals("33"))
            return R.drawable.eintrachtbraun;
        else if(count.equals("46"))
            return R.drawable.sandhausen;
        else if(count.equals("17"))
            return R.drawable.freiburg;
        else if(count.equals("14"))
            return R.drawable.nurnburg;
        else if(count.equals("30"))
            return R.drawable.fsvfrankfurt;
        else if(count.equals("721"))
            return R.drawable.leizpeig;
        else if(count.equals("21"))
            return R.drawable.grethuerfurth;
        else if(count.equals("32"))
            return R.drawable.karlsruher;
        else if(count.equals("44"))
            return R.drawable.heidenheim;
        else if(count.equals("26"))
            return R.drawable.tsv1860munchen;
        else if(count.equals("18"))
            return R.drawable.monchenglabach;
        else if(count.equals("29"))
            return R.drawable.scpaderborn;
        else if(count.equals("36"))
            return R.drawable.vflbochum;
        else if(count.equals("20"))
            return R.drawable.stpauli;
        else if(count.equals("38"))
            return R.drawable.arminiabielefeld;
        else if(count.equals("28"))
            return R.drawable.unionberlin;
        else if(count.equals("24"))
            return R.drawable.fortunadusseldorf;
        else if(count.equals("521"))
            return R.drawable.osclille;
        else if(count.equals("524"))
            return R.drawable.psg;
        else if(count.equals("531"))
            return R.drawable.troyesac;
        else if(count.equals("555"))
            return R.drawable.gazajaccio;
        else if(count.equals("516"))
            return R.drawable.olymmariselle;
        else if(count.equals("514"))
            return R.drawable.smcaen;
        else if(count.equals("522"))
            return R.drawable.ogcnice;
        else if(count.equals("548"))
            return R.drawable.asmonaco;
        else if(count.equals("543"))
            return R.drawable.nantes;
        else if(count.equals("538"))
            return R.drawable.eag;
        else if(count.equals("518"))
            return R.drawable.montipellier;
        else if(count.equals("532"))
            return R.drawable.angersco;
        else if(count.equals("536"))
            return R.drawable.scbastia;
        else if(count.equals("529"))
            return R.drawable.staderennais;
        else if(count.equals("526"))
            return R.drawable.gdbordeaux;
        else if(count.equals("547"))
            return R.drawable.stadedereims;
        else if(count.equals("511"))
            return R.drawable.toulouse;
        else if(count.equals("527"))
            return R.drawable.saintetienne;
        else if(count.equals("523"))
            return R.drawable.olympiquelyonnais;
        else if(count.equals("525"))
            return R.drawable.lorient;
        else if(count.equals("510"))
            return R.drawable.acajaccio;
        else if(count.equals("528"))
            return R.drawable.dijonfco;
        else if(count.equals("1045"))
            return R.drawable.paris;
        else if(count.equals("540"))
            return R.drawable.stadelavallois;
        else if(count.equals("557"))
            return R.drawable.chamoisniortais;
        else if(count.equals("515"))
            return R.drawable.valenciennes;
        else if(count.equals("556"))
            return R.drawable.nimesolympique;
        else if(count.equals("513"))
            return R.drawable.evianthonogaillard;
        else if(count.equals("567"))
            return R.drawable.redstar;
        else if(count.equals("573"))
            return R.drawable.uscreteil;
        else if(count.equals("541"))
            return R.drawable.clermontfoot;
        else if(count.equals("517"))
            return R.drawable.fcsm;
        else if(count.equals("1042"))
            return R.drawable.bourgperonnas;
        else if(count.equals("533"))
            return R.drawable.lehavre;
        else if(count.equals("519"))
            return R.drawable.ajauxerre;
        else if(count.equals("512"))
            return R.drawable.stadebrestois;
        else if(count.equals("545"))
            return R.drawable.fcmetz;
        else if(count.equals("546"))
            return R.drawable.rclens;
        else if(count.equals("520"))
            return R.drawable.asnancy;
        else if(count.equals("544"))
            return R.drawable.rctours;
        else if(count.equals("66"))
            return R.drawable.manunited;
        else if(count.equals("73"))
            return R.drawable.tottenham;
        else if(count.equals("1044"))
            return R.drawable.bournemouth;
        else if(count.equals("58"))
            return R.drawable.astonvilla;
        else if(count.equals("62"))
            return R.drawable.everton;
        else if(count.equals("346"))
            return R.drawable.watford;
        else if(count.equals("338"))
            return R.drawable.leicester;
        else if(count.equals("71"))
            return R.drawable.sunderland;
        else if(count.equals("68"))
            return R.drawable.norwich;
        else if(count.equals("354"))
            return R.drawable.crystalpalace;
        else if(count.equals("61"))
            return R.drawable.chelsea;
        else if(count.equals("72"))
            return R.drawable.swansea;
        else if(count.equals("67"))
            return R.drawable.newcastleuni;
        else if(count.equals("340"))
            return R.drawable.southampton;
        else if(count.equals("57"))
            return R.drawable.aresnal;
        else if(count.equals("563"))
            return R.drawable.westham;
        else if(count.equals("70"))
            return R.drawable.stokecity;
        else if(count.equals("64"))
            return R.drawable.liverpool;
        else if(count.equals("74"))
            return R.drawable.westbromwichalbion;
        else if(count.equals("65"))
            return R.drawable.mancity;
        else if(count.equals("560"))
            return R.drawable.rcdeportivo;
        else if(count.equals("92"))
            return R.drawable.realsociedad;
        else if(count.equals("80"))
            return R.drawable.rcdespanyol;
        else if(count.equals("82"))
            return R.drawable.getafe;
        else if(count.equals("78"))
            return R.drawable.atleticomadrid;
        else if(count.equals("275"))
            return R.drawable.udlaspalmas;
        else if(count.equals("87"))
            return R.drawable.rayovallecano;
        else if(count.equals("95"))
            return R.drawable.valencia;
        else if(count.equals("84"))
            return R.drawable.malaga;
        else if(count.equals("559"))
            return R.drawable.sevilla;
        else if(count.equals("77"))
            return R.drawable.athleticbilbao;
        else if(count.equals("81"))
            return R.drawable.barcelona;
        else if(count.equals("96"))
            return R.drawable.sportingdegijon;
        else if(count.equals("86"))
            return R.drawable.realmadrid;
        else if(count.equals("88"))
            return R.drawable.leventeud;
        else if(count.equals("558"))
            return R.drawable.rcceltadevigo;
        else if(count.equals("90"))
            return R.drawable.realbetis;
        else if(count.equals("94"))
            return R.drawable.villarreal;
        else if(count.equals("83"))
            return R.drawable.granada;
        else if(count.equals("278"))
            return R.drawable.sdeibar;
        else if(count.equals("595"))
            return R.drawable.cdmirandes;
        else if(count.equals("91"))
            return R.drawable.realzaragoza;
        else if(count.equals("1048"))
            return R.drawable.realoviedo;
        else if(count.equals("596"))
            return R.drawable.cdlugo;
        else if(count.equals("286"))
            return R.drawable.sdponferradina;
        else if(count.equals("285"))
            return R.drawable.elche;
        else if(count.equals("260"))
            return R.drawable.cdnumancia;
        else if(count.equals("254"))
            return R.drawable.cdtenerife;
        else if(count.equals("304"))
            return R.drawable.adalcorcon;
        else if(count.equals("89"))
            return R.drawable.rcdmallorca;
        else if(count.equals("744"))
            return R.drawable.uellagostera;
        else if(count.equals("79"))
            return R.drawable.caosasuna;
        else if(count.equals("299"))
            return R.drawable.huesca;
        else if(count.equals("263"))
            return R.drawable.deportivoalavis;
        else if(count.equals("267"))
            return R.drawable.udalmeria;
        else if(count.equals("745"))
            return R.drawable.cdleganes;
        else if(count.equals("265"))
            return R.drawable.gimnasticdetarragona;
        else if(count.equals("237"))
            return R.drawable.albacete;
        else if(count.equals("295"))
            return R.drawable.cordoba;
        else if(count.equals("250"))
            return R.drawable.realvalladolid;
        else if(count.equals("1046"))
            return R.drawable.athleticbilbao;
        else if(count.equals("298"))
            return R.drawable.gironafc;
        else if(count.equals("450"))
            return R.drawable.hellasverona;
        else if(count.equals("100"))
            return R.drawable.roma;
        else if(count.equals("109"))
            return R.drawable.juventus;
        else if(count.equals("115"))
            return R.drawable.udinesecalcio;
        else if(count.equals("114"))
            return R.drawable.uscittadipalermo;
        else if(count.equals("107"))
            return R.drawable.genoa;
        else if(count.equals("471"))
            return R.drawable.ussassuolocalcio;
        else if(count.equals("113"))
            return R.drawable.sscnapoli;
        else if(count.equals("584"))
            return R.drawable.ucsampdoria;
        else if(count.equals("713"))
            return R.drawable.carpifc;
        else if(count.equals("110"))
            return R.drawable.sslazio;
        else if(count.equals("103"))
            return R.drawable.bologna;
        else if(count.equals("108"))
            return R.drawable.intermilan;
        else if(count.equals("102"))
            return R.drawable.atlanta;
        else if(count.equals("470"))
            return R.drawable.frosinonecalcio;
        else if(count.equals("586"))
            return R.drawable.torino;
        else if(count.equals("99"))
            return R.drawable.acffiorentina;
        else if(count.equals("98"))
            return R.drawable.acmilan;
        else if(count.equals("445"))
            return R.drawable.empoli;
        else if(count.equals("106"))
            return R.drawable.chievoverona;
        else if(count.equals("1049"))
            return R.drawable.cdtondela;
        else if(count.equals("498"))
            return R.drawable.sportingclubdeportugal;
        else if(count.equals("711"))
            return R.drawable.belenenseslissabon;
        else if(count.equals("496"))
            return R.drawable.rioave;
        else if(count.equals("503"))
            return R.drawable.porto;
        else if(count.equals("502"))
            return R.drawable.vitoriasc;
        else if(count.equals("506"))
            return R.drawable.vitoriafc;
        else if(count.equals("810"))
            return R.drawable.boavistafc;
        else if(count.equals("1052"))
            return R.drawable.uniaomadeira;
        else if(count.equals("504"))
            return R.drawable.maritimofunchal;
        else if(count.equals("583"))
            return R.drawable.moreirense;
        else if(count.equals("712"))
            return R.drawable.fcarouca;
        else if(count.equals("497"))
            return R.drawable.scbraga;
        else if(count.equals("501"))
            return R.drawable.cdnacional;
        else if(count.equals("495"))
            return R.drawable.slbenefica;
        else if(count.equals("582"))
            return R.drawable.gdestorilpraia;
        else if(count.equals("507"))
            return R.drawable.pacosdeferreira;
        else if(count.equals("509"))
            return R.drawable.academicadecoimbra;
        else if(count.equals("1054"))
            return R.drawable.fcmagdeburg;
        else if(count.equals("40"))
            return R.drawable.rcrotweib;
        else if(count.equals("39"))
            return R.drawable.svwehenwiesbaden;
        else if(count.equals("1055"))
            return R.drawable.wurzburgerkickers;
        else if(count.equals("34"))
            return R.drawable.fchansa;
        else if(count.equals("56"))
            return R.drawable.werderbremen;
        else if(count.equals("202"))
            return R.drawable.stuttgarterkickers;
        else if(count.equals("204"))
            return R.drawable.fortunakoln;
        else if(count.equals("51"))
            return R.drawable.preubenmunchen;
        else if(count.equals("741"))
            return R.drawable.sgsonnenhofgrobaspachev;
        else if(count.equals("720"))
            return R.drawable.kielersvholstein;
        else if(count.equals("740"))
            return R.drawable.fsvmainz;
        else if(count.equals("22"))
            return R.drawable.erzgebirgeaue;
        else if(count.equals("52"))
            return R.drawable.vflosnabruck;
        else if(count.equals("35"))
            return R.drawable.desden;
        else if(count.equals("45"))
            return R.drawable.vfbstuttgart;
        else if(count.equals("50"))
            return R.drawable.vfralen;
        else if(count.equals("54"))
            return R.drawable.chemnitzer;
        else if(count.equals("23"))
            return R.drawable.energiecottbus;
        else if(count.equals("554"))
            return R.drawable.hallescher;
        else if(count.equals("665"))
            return R.drawable.rodajckerkrade;
        else if(count.equals("671"))
            return R.drawable.heraclesalmelo;
        else if(count.equals("675"))
            return R.drawable.feyenoordrotterdam;
        else if(count.equals("676"))
            return R.drawable.utrecht;
        else if(count.equals("682"))
            return R.drawable.azalkmaar;
        else if(count.equals("678"))
            return R.drawable.afcajax;
        else if(count.equals("672"))
            return R.drawable.wiilem;
        else if(count.equals("679"))
            return R.drawable.vitessearnhem;
        else if(count.equals("680"))
            return R.drawable.adodenhaag;
        else if(count.equals("674"))
            return R.drawable.psveindhoven;
        else if(count.equals("673"))
            return R.drawable.scheerenveen;
        else if(count.equals("669"))
            return R.drawable.degraafschap;
        else if(count.equals("677"))
            return R.drawable.fcgroningen;
        else if(count.equals("666"))
            return R.drawable.twenteinsite;
        else if(count.equals("684"))
            return R.drawable.peczwolle;
        else if(count.equals("717"))
            return R.drawable.sccambuur;
        else if(count.equals("667"))
            return R.drawable.necnijmegen;
        else if(count.equals("670"))
            return R.drawable.excelsior;
        else if(count.equals("749"))
            return R.drawable.malmoff;
        else if(count.equals("1056"))
            return R.drawable.astana;
        else if(count.equals("610"))
            return R.drawable.galatasaraysk;
        else if(count.equals("751"))
            return R.drawable.cskamoscow;
        else if(count.equals("724"))
            return R.drawable.waxtap;
        else if(count.equals("731"))
            return R.drawable.zenitstpetersburg;
        else if(count.equals("842"))
            return R.drawable.dynamokyiv;
        else if(count.equals("971"))
            return R.drawable.maccabitelaviv;
        else if(count.equals("654"))
            return R.drawable.olympiacos;
        else if(count.equals("755"))
            return R.drawable.gnkdinamozagreb;
        else if(count.equals("748"))
            return R.drawable.fkbatebaryssau;
        else if(count.equals("1057"))
            return R.drawable.kaagentstumnummer;
        return android.R.drawable.ic_dialog_alert;
    }
    public static String formatPoint(Context context,int point)
    {
        return String.format(context.getString(R.string.format_points),point);
    }
    public static String formatPosition(Context context,int position)
    {
        return String.format(context.getString(R.string.format_position),position);
    }
    public static String formatMatches(Context context,int matches)
    {
        return String.format(context.getString(R.string.format_matches_played),matches);
    }
    public static String formatWins(Context context,int wins)
    {
        return String.format(context.getString(R.string.team_wins),wins);
    }
    public static String formatLosses(Context context,int losses)
    {
        return String.format(context.getString(R.string.team_losses),losses);
    }
    public static String formatDraws(Context context,int draws)
    {
        return String.format(context.getString(R.string.team_draws),draws);
    }
    public static String formatGoalAgainst(Context context,int goalsagainst)
    {
        return String.format(context.getString(R.string.team_goals_against),goalsagainst);
    }
    public static String formatGoalDifference(Context context,int goaldifference)
    {
        return String.format(context.getString(R.string.team_goal_difference),goaldifference);
    }
    public static String formatGoals(Context context,int goals)
    {
        return String.format(context.getString(R.string.team_goals),goals);
    }
    public static String formatValue(Context context,String valueMarket)
    {
        return String.format(context.getString(R.string.market_value),valueMarket);
    }
    public static String formatNationality(Context context,String nationality)
    {
        return String.format(context.getString(R.string.format_nationality),nationality);
    }
    public static String formatDOB(Context context,String dobs)
    {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = simpleDateFormat.parse(dobs);
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd MMM,yyyy");
            String dob = simpleDateFormat1.format(date);
            return String.format(context.getString(R.string.format_dob),dob);
        }catch (ParseException e)
        {
        }
        return null;
    }
    public static String formatMarketValue(Context context,String market)
    {
        return String.format(context.getString(R.string.format_market),market);
    }
    public static String formatContract(Context context,String contractUntil)
    {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = simpleDateFormat.parse(contractUntil);
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd MMM,yyyy");
            String contract = simpleDateFormat1.format(date);
            return String.format(context.getString(R.string.format_contract), contract);
            }catch (ParseException e)
            {
            }
        return null;
        }
    public static String formatJersey(Context context,String jersey)
    {
        return String.format(context.getString(R.string.format_jersey),jersey);
    }
    public static String formatPlayerPosition(Context context,String pos)
    {
        return String.format(context.getString(R.string.format_position),pos);
    }

}
