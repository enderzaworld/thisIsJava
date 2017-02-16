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
    
    public boolean isGlobe(String Number){
        return "globe".equals(getNumberIdentity(Number));
    }
    
    public boolean isSmart(String Number){
        return "smart".equals(getNumberIdentity(Number));
    }
    
    public boolean isSun(String Number){
        return "sun".equals(getNumberIdentity(Number));
    }
    
    public String getNumberIdentity(String Number){
	if(Number.length()==11){
		switch(Number.substring(0, 4)){
                    //smart
                    case "0911"://smart? unreliable source
                    case "0913"://smart? unreliable source
                    case "0914"://smart? unreliable source
                    case "0989"://smart? unreliable source
                    case "0813":
                    case "0907":
                    case "0908":
                    case "0909":
                    case "0910":
                    case "0912":
                    case "0918":
                    case "0919":
                    case "0920":
                    case "0921":
                    case "0928":
                    case "0929":
                    case "0930":// Formerly Red Mobile
                    case "0938":// Formerly Red Mobile
                    case "0939":// Formerly Red Mobile
                    case "0946":
                    case "0947":
                    case "0948":
                    case "0949":
                    case "0950":
                    case "0998":
                    case "0999":
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
