package fr.eni.appliTrocEnchere.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Cette classe permet de lire le contenu du fichier messages_erreur.properties
 * @author Administrator
 *
 */
public abstract class LecteurMessage {
	private static ResourceBundle rb;
	
	static
	{
		try
		{
			rb = ResourceBundle.getBundle("fr.eni.appliTrocEnchere.exception.messages_erreur");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * @param code
	 * @return
	 */
	public static  String getMessageErreur(int code)
	{
		String message="";
		try
		{
			if(rb!=null)
			{
				message = rb.getString(String.valueOf(code));
			}
			else
			{
				message="Problème à la lecture du fichier contenant les messages";
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			message="Une erreur inconnue est survenue";
		}
		return message;
	}
	
	public static List<String> codesErreurToString (BusinessException e){
		List<Integer> listeErreurs = new ArrayList<>();
		List<String> errorMessages = new ArrayList<>();
		listeErreurs = e.getListeCodesErreur();
		for (Integer code : listeErreurs) {
			errorMessages.add(LecteurMessage.getMessageErreur(code));
		}
		return errorMessages;
	}
}