package ps.dao.impl;

import java.util.ArrayList;
import java.util.List;
import ps.benefits.BenEnrollment;

/* Benefits dao interface; returns hard-coded data set  */

public class BenefitsDaoHCImpl implements ps.dao.BenefitsDao {

   public BenefitsDaoHCImpl() {
      System.out.println( "BenefitsDaoHCImpl constructor" );
   }



   public List<BenEnrollment> getLifeADDEnrollments( String company ) {

      if( company.equals( "FXS" ) ) {
         return getFXSData();
      } else if( company.equals( "G9P" ) ) {
         return null;
      } else {
         return null;
      }

   }

   private List<BenEnrollment> getFXSData() {
      List<BenEnrollment> life = new ArrayList<BenEnrollment>();

      life.add( new BenEnrollment( "00001146445", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001526752", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001526752", "2Y", "000SRQ" ) );
      life.add( new BenEnrollment( "00001526874", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001526923", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527051", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527068", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527073", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527077", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527084", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527084", "2Y", "000SRQ" ) );
      life.add( new BenEnrollment( "00001527093", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527093", "2Y", "000SRQ" ) );
      life.add( new BenEnrollment( "00001527094", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527094", "2Y", "000SRQ" ) );
      life.add( new BenEnrollment( "00001527097", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527099", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527099", "2Y", "000SRQ" ) );
      life.add( new BenEnrollment( "00001527100", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527102", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527103", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527103", "2Y", "000SRQ" ) );
      life.add( new BenEnrollment( "00001527112", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527112", "2Y", "000SRQ" ) );
      life.add( new BenEnrollment( "00001527114", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527114", "2Y", "000SRQ" ) );
      life.add( new BenEnrollment( "00001527118", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527122", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527134", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527134", "2Y", "000SRQ" ) );
      life.add( new BenEnrollment( "00001527136", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527136", "2Y", "000SRQ" ) );
      life.add( new BenEnrollment( "00001527141", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527150", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527152", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527152", "2Y", "000SRQ" ) );
      life.add( new BenEnrollment( "00001527153", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527153", "2Y", "000SRQ" ) );
      life.add( new BenEnrollment( "00001527156", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527163", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527166", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527169", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527169", "2Y", "000SRQ" ) );
      life.add( new BenEnrollment( "00001527175", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527175", "2Y", "000SRQ" ) );
      life.add( new BenEnrollment( "00001527176", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527183", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527184", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527184", "2Y", "000SRQ" ) );
      life.add( new BenEnrollment( "00001527185", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527185", "2Y", "000SRQ" ) );
      life.add( new BenEnrollment( "00001527188", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527191", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527191", "2Y", "000SRQ" ) );
      life.add( new BenEnrollment( "00001527192", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527194", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527202", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527202", "2Y", "000SRQ" ) );
      life.add( new BenEnrollment( "00001527204", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527204", "2Y", "000SRQ" ) );
      life.add( new BenEnrollment( "00001527206", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527208", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527208", "2Y", "000SRQ" ) );
      life.add( new BenEnrollment( "00001527213", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527220", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527230", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527239", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527243", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527243", "2Y", "000SRQ" ) );
      life.add( new BenEnrollment( "00001527259", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527259", "2Y", "000SRQ" ) );
      life.add( new BenEnrollment( "00001527266", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527269", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527270", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527275", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527275", "2Y", "000SRQ" ) );
      life.add( new BenEnrollment( "00001527282", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527282", "2Y", "000SRQ" ) );
      life.add( new BenEnrollment( "00001527285", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527291", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527292", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527293", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527300", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527300", "2Y", "000SRQ" ) );
      life.add( new BenEnrollment( "00001527306", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527306", "2Y", "000SRQ" ) );
      life.add( new BenEnrollment( "00001527311", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527324", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527324", "2Y", "000SRQ" ) );
      life.add( new BenEnrollment( "00001527325", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527325", "2Y", "000SRQ" ) );
      life.add( new BenEnrollment( "00001527333", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527337", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527339", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527340", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527348", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527352", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527353", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527357", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527363", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527363", "2Y", "000SRQ" ) );
      life.add( new BenEnrollment( "00001527365", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527368", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527374", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527375", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527379", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527387", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527387", "2Y", "000SRQ" ) );
      life.add( new BenEnrollment( "00001527390", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527391", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527391", "2Y", "000SRQ" ) );
      life.add( new BenEnrollment( "00001527394", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527397", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527397", "2Y", "000SRQ" ) );
      life.add( new BenEnrollment( "00001527399", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527403", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527403", "2Y", "000SRQ" ) );
      life.add( new BenEnrollment( "00001527404", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001527404", "2Y", "000SRQ" ) );
      life.add( new BenEnrollment( "00001801548", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001811715", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001834515", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001914836", "21", "000SRP" ) );
      life.add( new BenEnrollment( "00001916219", "21", "000SRP" ) );

      //simulate some I/O delay
      try {
         Thread.sleep( 2000 );
      } catch ( Exception e ) {
         e.printStackTrace();
      }

      return life;
   }

}
