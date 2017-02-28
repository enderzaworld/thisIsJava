/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisisjava;

/**
 *
 * @author Imba Gamer
 */
public class NumberValidation {
    
    public String getGateway(String Network){
        switch(Network.toLowerCase()){
            case "smart":
            case "smartbro":
            case "tnt":
                return "8724";
            case "sun":
            case "globe":
            case "tm":
            case "abs-cbn":
                return "GATEWAY";
            default:
                return null;
        }
    }
    
    public boolean isGlobe(String Number){
        return "globe".equals(getNumberIdentity(Number).toLowerCase());
    }
    
    public boolean isSmart(String Number){
        String net = getNumberIdentity(Number).toLowerCase();
        return ("smart".equals(net)||"smartbro".equals(net)||"tnt".equals(net));
    }
    
    public boolean isSun(String Number){
        return "sun".equals(getNumberIdentity(Number).toLowerCase());
    }
    
    public String getNumberIdentity(String Number){
	if(Number.length()==11){
		switch(Number.substring(0, 4)){
                    //smart
                    case "0911"://smart? unreliable source
                    case "0913"://smart? unreliable source
                    case "0914"://smart? unreliable source
                    case "0989"://smart? unreliable source
                    case "0813"://postpaid
                    case "0907"://TNT - prepaid
                    case "0908"://TNT - prepaid
                    case "0909"://TNT - prepaid
                    case "0910"://TNT - prepaid
                    case "0912"://TNT - prepaid
                    case "0918"://TNT - prepaid
                    case "0919"://TNT - prepaid
                    case "0920"://TNT - prepaid
                    case "0921"://TNT - prepaid
                    case "0928"://TNT - prepaid
                    case "0929"://TNT - prepaid
                    case "0930"://TNT Formerly Red Mobile
                    case "0938"://TNT Formerly Red Mobile
                    case "0939"://TNT Formerly Red Mobile
                    case "0946"://TNT - prepaid
                    case "0947"://TNT - prepaid
                    case "0948"://TNT - prepaid
                    case "0949"://TNT - prepaid
                    case "0950"://TNT - prepaid
                    case "0998"://TNT - prepaid
                    case "0999"://TNT - prepaid
                        return "smart";
                    //sun
                    case "0922":
                    case "0923":
                    case "0924":
                    case "0925":
                    case "0931":
                    case "0932":
                    case "0933":
                    case "0934":
                    case "0942":
                    case "0943":
                        return "sun";
                    //globe
                    case "0817":// - postpaid
                    case "0945":// - postpaid
                    case "0976":// - postpaid
                    case "0994":// - postpaid
                    case "0995":// - prepaid
                    case "0996":// - prepaid
                    case "0997":// - prepaid
                    case "0905":// - prepaid
                    case "0906":// - prepaid
                    case "0915":// - prepaid
                    case "0916":// - prepaid
                    case "0917":// - prepaid
                    case "0926":// - prepaid
                    case "0927":// - prepaid
                    case "0935":// - prepaid
                    case "0936":// - prepaid
                    case "0975":// - prepaid
                    case "0977":// - TM former Nextel - Prepaid | - TM formerly Next mobile - Prepaid
                        return "globe";
                    case "0937":// - abs
                        return "abs";
                    case "0978"://globe? unreliable source| tm
                    case "0979"://globe? unreliable source| tm
                        return "globe";
                    case "0973"://Exetel (Express Communications)
                    case "0974"://Exetel (Express Communications)
                        return "extel";
                    default:
                        return "unused";
                }
	}else{
            return "invalid";
	}
    }
}
