import java.io.File;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;

public class DirectoryReader {

	static int spc_count=-1;
	static int counter=0;
	static int mkvCounter=0;
	static int mp4Counter=0;
	static int aviCounter=0;
	static int unknownCounter=0;
	static ArrayList<String> Ar1 = new ArrayList<String>();
	//static ArrayList<String> Ar2= new ArrayList<String>();

	static void Process(File aFile, int folderSequence) {
		spc_count++;
		String spcs = "";
		//String videoExtension[] = {"mp4", "mkv"};
		for (int i = 0; i < spc_count; i++)
			spcs += " ";
		if(aFile.isFile())
		{
			String fileExtension= aFile.getName().substring(aFile.getName().length() -3, aFile.getName().length());
			if(fileExtension.equalsIgnoreCase("mp4")||fileExtension.equalsIgnoreCase("mkv")||fileExtension.equalsIgnoreCase("avi"))
			{
				counter++;

				if(folderSequence==0)
					Ar1.add(aFile.getName());
				/*if(folderSequence==1)
					Ar2.add(aFile.getName().toLowerCase());
				 */
				if (fileExtension.equalsIgnoreCase("mkv"))
				{
					mkvCounter++;
				}
				else if (fileExtension.equalsIgnoreCase("mp4"))
				{
					mp4Counter++;
				}
				else if (fileExtension.equalsIgnoreCase("avi"))
				{
					aviCounter++;
				}
				else{
					unknownCounter++;
				}

				//Print details
				//System.out.println(spcs +counter+ " " + aFile.getName() + "Extension: " + fileExtension);
				//System.out.println(counter+ " " + aFile.getName()+"			Path: " + aFile.getAbsolutePath() );
				//System.out.println(counter+ " " + aFile.getName());

			}

		}

		else if (aFile.isDirectory()) {
			//System.out.println(spcs + "[DIR] " + aFile.getName());
			File[] listOfFiles = aFile.listFiles();
			if(listOfFiles!=null) {
				for (int i = 0; i < listOfFiles.length; i++)
					Process(listOfFiles[i], folderSequence);
			} else {
				System.out.println(spcs + " [ACCESS DENIED]");
			}
		}
		spc_count--;

	}
	static String cleanString(String token1)
	{
		//System.out.println("Passed String:"+token1);
		StringBuffer s = new StringBuffer(token1.length()-4);

		String FExt=token1.substring(token1.lastIndexOf('.'));
		token1=token1.substring(0, token1.length()-4);
		//System.out.println("Split strings:" + FExt+ "and "+token1);
		CharacterIterator it = new StringCharacterIterator(token1);
		for (char ch = it.first(); ch != CharacterIterator.DONE; ch = it.next()) {
			switch (ch) {
			case '.':
				s.append(" ");
				break;
			case '_':
				s.append(" ");
				break;
			case '-':
				s.append(" ");
				break;
			case '!':
				s.append(" ");
				break;
			default:
				s.append(ch);
				break;
			}
		}

		token1 = s.toString();

		token1=token1+FExt;
		//System.out.println("Cleaned string:"+token1);
		return token1;
	}

	public static void main(String[] args) {
		String nam[] = {"Z:/HD Movies/","Z:/Movies/", "Z:/Hindi/"};
		for(int i=0;i<nam.length;i++){
			File aFile = new File(nam[i]);


			Process(aFile, i);
		}
		//Iterator iter=new Ar1.iterator(); 

int newCounter=0;
		for (String token1 : Ar1)
		{
			newCounter++;
			token1=cleanString(token1);
			System.out.println(newCounter +" "+ token1);
		}
		/*for (String token2 : Ar2)
		{
			newCounter++;

			token2=cleanString(token2);
			System.out.println(newCounter +" "+ token1);

		}
*/

		//System.out.println("Values in AR1:"+Ar1.toString());
		//System.out.println("---------------------------------------------------------------------");
		//System.out.println("Values in AR2:"+Ar2.toString());
		System.out.println("---------------------------------------------------------------------");

		System.out.println("---------------------------------------------------------------------");
		System.out.println("Final Stats:");
		System.out.println("Total Video Files Found:" + counter + "  in folder "+ nam);
		System.out.println("Total MP4 Files Found:" + mp4Counter);
		System.out.println("Total MKV Files Found:" + mkvCounter);
		System.out.println("Total AVI Files Found:" + aviCounter);
		System.out.println("Total Other Format Files Found:" + unknownCounter);

	}

}

