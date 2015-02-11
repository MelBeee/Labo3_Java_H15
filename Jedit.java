// Jedit.java
// Jedit est un éditeur de texte très minimaliste en mode ligne
// Fait par Melissa Boucher et Francis Thibodeau 
// 13 Fevrier 2015

public class Jedit
{
   public void editer( String args[] )
   {
      if(args.length > 0)
		{
         String option = "" ;
         String nomFichier = "";
         
         if(args.length == 1)
         {
            nomFichier = args[0];
         }
         else
         {
            option = args[0];
            nomFichier = args[1];
         }
         
         System.out.println(option);
         System.out.println(nomFichier); 
		}
		else
		{
         // Si l'utilisateur a entré zero ou plus de deux parametre
			System.out.println( "Entrez minimalement un parametre et maximalement 2 parametres.");
		}
   }
   
   public static void main( String args[] )
   {
      Jedit app = new Jedit();
      app.editer( args );
   }
}