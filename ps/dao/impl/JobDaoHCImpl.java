package ps.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import ps.dao.JobDao;
import ps.hcm.PsJob;

/* JOB dao HC (hardcoded) data implementation  */

public class JobDaoHCImpl implements JobDao {

   public JobDaoHCImpl() {
      System.out.println( "JobDaoHCImpl constructor" );
   }


   public List<PsJob> getEmployeesOfCompany( String company ) {

      if( company.equals( "FXS" ) ) {
         return getFXSData();
      } else if( company.equals( "G9P" ) ) {
         return null;
      } else {
         return getSampleData();
      }
   }


   private List<PsJob> getSampleData() {
      List<PsJob> jobRecords = new ArrayList<PsJob>();

      jobRecords.add( new PsJob( new BigDecimal( "320000" ), new BigDecimal( "321321" ) ) );
      jobRecords.add( new PsJob( new BigDecimal( "136000" ), new BigDecimal( "131000" ) ) );
      jobRecords.add( new PsJob( new BigDecimal( "52800" ) ));
      jobRecords.add( new PsJob( new BigDecimal( "157500" ), new BigDecimal( "152000" ) ) );
      jobRecords.add( new PsJob( new BigDecimal( "82600" ), new BigDecimal( "75500" ) ) );
      jobRecords.add( new PsJob( new BigDecimal( "60000" ), BigDecimal.ZERO ) );

      //simulate some I/O delay
      try {
         Thread.sleep( 2000 );
      } catch ( Exception e ) {
         e.printStackTrace();
      }

      return jobRecords;
   }


   private List<PsJob> getFXSData() {
      List<PsJob> jobRecords = new ArrayList<PsJob>();

      jobRecords.add( new PsJob( "00001146445", new BigDecimal( "250000" ) , new BigDecimal( "250000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001526752", new BigDecimal( "102000" ) , new BigDecimal( "102000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001526874", new BigDecimal( "110000" ) , new BigDecimal( "110000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001526923", new BigDecimal( "320000" ) , new BigDecimal( "320000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527051", new BigDecimal( "400000" ) , new BigDecimal( "400000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527068", new BigDecimal( "76500" )  , new BigDecimal( "76500" )  , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527073", new BigDecimal( "125000" ) , new BigDecimal( "125000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527077", new BigDecimal( "80000" )  , new BigDecimal( "80000" )  , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527084", new BigDecimal( "190000" ) , new BigDecimal( "190000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527093", new BigDecimal( "225000" ) , new BigDecimal( "260000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527094", new BigDecimal( "325000" ) , new BigDecimal( "325000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527097", new BigDecimal( "285000" ) , new BigDecimal( "285000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527099", new BigDecimal( "130000" ) , new BigDecimal( "130000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527100", new BigDecimal( "80000" )  , new BigDecimal( "80000" )  , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527102", new BigDecimal( "320000" ) , new BigDecimal( "320000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527103", new BigDecimal( "425000" ) , new BigDecimal( "425000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527112", new BigDecimal( "300000" ) , new BigDecimal( "300000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527114", new BigDecimal( "285000" ) , new BigDecimal( "285000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527118", new BigDecimal( "85000" )  , new BigDecimal( "102500" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527122", new BigDecimal( "340000" ) , new BigDecimal( "340000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527134", new BigDecimal( "165000" ) , new BigDecimal( "165000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527136", new BigDecimal( "325000" ) , new BigDecimal( "325000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527141", new BigDecimal( "125000" ) , new BigDecimal( "125000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527150", new BigDecimal( "115000" ) , new BigDecimal( "115000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527152", new BigDecimal( "163000" ) , new BigDecimal( "163000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527153", new BigDecimal( "145000" ) , new BigDecimal( "145000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527156", new BigDecimal( "45000" )  , new BigDecimal( "45000" )  , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527163", new BigDecimal( "80000" )  , new BigDecimal( "80000" )  , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527166", new BigDecimal( "425000" ) , new BigDecimal( "425000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527169", new BigDecimal( "232300" ) , new BigDecimal( "265000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527175", new BigDecimal( "219300" ) , new BigDecimal( "235000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527176", new BigDecimal( "235000" ) , new BigDecimal( "265000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527183", new BigDecimal( "112500" ) , new BigDecimal( "112500" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527184", new BigDecimal( "500000" ) , new BigDecimal( "500000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527185", new BigDecimal( "260000" ) , new BigDecimal( "260000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527188", new BigDecimal( "195000" ) , new BigDecimal( "195000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527191", new BigDecimal( "350000" ) , new BigDecimal( "350000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527192", new BigDecimal( "400000" ) , new BigDecimal( "400000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527194", new BigDecimal( "82500" )  , new BigDecimal( "100000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527202", new BigDecimal( "500000" ) , new BigDecimal( "500000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527204", new BigDecimal( "94000" )  , new BigDecimal( "94000" )  , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527206", new BigDecimal( "95000" )  , new BigDecimal( "95000" )  , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527208", new BigDecimal( "285000" ) , new BigDecimal( "310000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527213", new BigDecimal( "260000" ) , new BigDecimal( "290000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527220", new BigDecimal( "82500" )  , new BigDecimal( "100000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527230", new BigDecimal( "260000" ) , new BigDecimal( "260000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527239", new BigDecimal( "202000" ) , new BigDecimal( "202000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527243", new BigDecimal( "180000" ) , new BigDecimal( "198000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527259", new BigDecimal( "170000" ) , new BigDecimal( "170000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527266", new BigDecimal( "325000" ) , new BigDecimal( "325000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527269", new BigDecimal( "80000" )  , new BigDecimal( "80000" )  , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527270", new BigDecimal( "170000" ) , new BigDecimal( "170000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527275", new BigDecimal( "120000" ) , new BigDecimal( "120000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527282", new BigDecimal( "185000" ) , new BigDecimal( "185000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527285", new BigDecimal( "700000" ) , new BigDecimal( "700000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527291", new BigDecimal( "400000" ) , new BigDecimal( "400000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527292", new BigDecimal( "400000" ) , new BigDecimal( "400000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527293", new BigDecimal( "35100" )  , new BigDecimal( "700000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527300", new BigDecimal( "131000" ) , new BigDecimal( "131000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527306", new BigDecimal( "400000" ) , new BigDecimal( "550000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527311", new BigDecimal( "520000" ) , new BigDecimal( "125000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527324", new BigDecimal( "250000" ) , new BigDecimal( "250000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527325", new BigDecimal( "140000" ) , new BigDecimal( "140000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527333", new BigDecimal( "400000" ) , new BigDecimal( "400000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527337", new BigDecimal( "325000" ) , new BigDecimal( "325000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527339", new BigDecimal( "52000" )  , new BigDecimal( "52000" )  , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527340", new BigDecimal( "52000" )  , new BigDecimal( "52000" )  , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527348", new BigDecimal( "110000" ) , new BigDecimal( "110000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527352", new BigDecimal( "120000" ) , new BigDecimal( "120000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527353", new BigDecimal( "175000" ) , new BigDecimal( "175000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527357", new BigDecimal( "255000" ) , new BigDecimal( "255000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527363", new BigDecimal( "105000" ) , new BigDecimal( "105000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527365", new BigDecimal( "320000" ) , new BigDecimal( "320000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527368", new BigDecimal( "195000" ) , new BigDecimal( "195000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527374", new BigDecimal( "154000" ) , new BigDecimal( "154000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527375", new BigDecimal( "400000" ) , new BigDecimal( "400000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527379", new BigDecimal( "140000" ) , new BigDecimal( "140000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527387", new BigDecimal( "140000" ) , new BigDecimal( "140000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527390", new BigDecimal( "89000" )  , new BigDecimal( "89000" )  , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527391", new BigDecimal( "300000" ) , new BigDecimal( "300000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527394", new BigDecimal( "300900" ) , new BigDecimal( "300900" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527397", new BigDecimal( "215000" ) , new BigDecimal( "215000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527399", new BigDecimal( "310000" ) , new BigDecimal( "310000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527403", new BigDecimal( "290700" ) , new BigDecimal( "290700" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001527404", new BigDecimal( "175000" ) , new BigDecimal( "195000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001801548", new BigDecimal( "120000" ) , new BigDecimal( "120000" ) , "UJ9" ) );
      jobRecords.add( new PsJob( "00001811715", new BigDecimal( "60000" )  , new BigDecimal( "60000" )  , "UJ9" ) );
      jobRecords.add( new PsJob( "00001834515", new BigDecimal( "80000" )  , new BigDecimal( "80000" )  , "UJ9" ) );
      jobRecords.add( new PsJob( "00001914836", new BigDecimal( "0" )      , new BigDecimal( "75000" )  , "UJ9" ) );
      jobRecords.add( new PsJob( "00001916219", new BigDecimal( "0" )      , new BigDecimal( "90000" )  , "UJ9" ) );

      //simulate some I/O delay
      try {
         Thread.sleep( 2000 );
      } catch ( Exception e ) {
         e.printStackTrace();
      }

      return jobRecords;
   }


}