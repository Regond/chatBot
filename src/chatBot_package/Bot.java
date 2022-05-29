package chatBot_package;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JOptionPane;

public class Bot {
	//����������� ������
	List<String> list = new ArrayList<>();
	String[] questions; //�������� ������� �� �� ��� ����� ��������
	String[] answers; //�������� ������ ����
	String[] answers_ua; //��� ������
	String[] questions_ua; //��� �������
	String answer; //�����, ��� ���������� ������� ����
	
	//���� ��� ���
	boolean gameisOver = true;
	boolean gameisOverUa = true;
	int cnt = 0;
	String checkLast = "";		
	String checkLast_ua = "";
	Clip b;
	
	
	
	public String chatting(String text) {
		readAllFiles();
		answer = "";
		gameisOverUa = true;
		String textCopy = text.toLowerCase();
		if(textCopy.contains("game")) {
			gameisOver = false;
			//answerText("Game is started.");
			//answerText("Use /rules command to learn the rules of the game ");
			String[] words = {"Shop", "Scale", "Crowd", "Crown", "Creed", "Paradise", "Prom", "Aegis", "Lugia", "Breakdance"};
			double a = Math.random()*9;
			long rnd = Math.round(a);
			answer = "Let me start. My first word is..." + "\n"+ words[(int) rnd];
			checkLast += words[(int) rnd].substring(words[(int) rnd].length()-1).toLowerCase();
		}
		if(textCopy.equals("quit")) {
			gameisOver = true;
			answer = "Game is over. Your score: " + Integer.toString(cnt+1);
			cnt = 0;
		}
		if (text.equals("")) {JOptionPane.showMessageDialog(null, "Enter the text!");}
		else 
		{	
			if(gameisOver) {
			if (textCopy.contains("date")) {
				 SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z"); 
				 Date date = new Date(System.currentTimeMillis()); 
				 String str = formatter.format(date); 
				 answer = "Current date is: " + str;

			}
			else if(textCopy.contains("hello") || textCopy.contains("hi") || textCopy.contains("sup") || textCopy.contains("salut") ||
					textCopy.contains("greetings")) {
					answer = greeting();
			}
			else if(text.equals("/help")) {
				answer = "Help: " + "\n"
						+ "I can speak with you only with simple phrases:" + "\n"
						+ "Date - show current time" + "\n"
						+ "Game - to play the word game" + "\n"
						+ "/rules - rules of word game."
						;

			}
			else if(textCopy.equals("/rules")){
				answer = "After the start of the game, you must write any word, and the bot in response will write you a word that begins with the last letter of the word that you entered, in turn, you must also enter a word that begins with the letter that ends with the word of the bot.";

			}
			else 
			{
				for(int i = 0; i < questions.length; i++) {
					if (textCopy.contains(questions[i])) answer = answers[i];
				}
				if (answer == "") {
					answer = random();
				} 

			}
		}
		else {
			if(!textCopy.contains("game")) {
				answer = gaming(text);
			}
			
		}

		}
		
		return answer;
}
	
	public String random() {
		double a = Math.random()*3;
		long rnd = Math.round(a);
		if(rnd == 1) return "Idk what you talking about!";
		if(rnd == 2) return "Sorry, i don't understand you.";
		if(rnd == 3) return "Check the help. Write /help";
		return "Try again";
	}

	public String greeting() {
		double a = Math.random()*9;
		long rnd = Math.round(a);
		if(rnd == 1) return "Hello! :3";
		if(rnd == 2) return "Salut, my Friend!";
		if(rnd == 3) return "Hi! Glad to see you";
		if(rnd == 4) return "Hey!";
		if(rnd == 5) return "Wassup ;)";
		if(rnd == 6) return "Yo!!!!!";
		if(rnd == 7) return "Hola-hola";
		if(rnd == 8) return "Howdy!!!!";
		if(rnd == 9) return "Look, who's here! Greetings.";
		return "Hiiiiiii :3";
	}
	
	public void readFile(String name) throws IOException {
		// = new BufferedReader(new FileReader(name));
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(name), "UTF8"));
		String str;

		ArrayList<String> list = new ArrayList<String>();
		while((str = reader.readLine()) != null ){
		    if(!str.isEmpty()){
		    list.add(str);	
		    }
		}
		if(name == "questions.txt") questions = list.toArray(new String[0]);
		else if (name == "answers.txt") answers = list.toArray(new String[0]);
		else if(name == "answers_ua.txt") answers_ua = list.toArray(new String[0]);
		else if(name == "questions_ua.txt") questions_ua = list.toArray(new String[0]);
			
}	
	
	public void readAllFiles() {
		try {
			readFile("questions.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			readFile("answers.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			readFile("questions_ua.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			readFile("answers_ua.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public String gaming(String text) {
		text = text.toLowerCase();
		String last = "";
		String ret = "";
		if (text.equals("/rules")){
			ret = "After the start of the game, you must write any word, and the bot in response will write you a word that begins with the last letter of the word that you entered, in turn, you must also enter a word that begins with the letter that ends with the word of the bot.";
		}
		else {
		if(text.length() > 1) {
			last = text.substring(text.length()-1);
		}
		else {
			last = text;
		}
		
		if((text.substring(0, 1).equals(checkLast.substring(checkLast.length()-1)))) {
			double a = Math.random()*9;
			long rnd = Math.round(a);
			String[] aArr = {"Address ", "Arctica", "Australia", "Advertisement", "Agency ", "Anatomy", "Austria", "Arrow", "Array", "Ambulance"};
			String[] bArr = {"Baby", "Bottom", "Boolean", "Box", "Break ", "Breakfast", "Bottle", "Bucks", "Bread", "Binance"};
			String[] cArr = {"Corn", "Content", "Contest", "Carrot", "Creatin", "Clown", "Clock", "Certain", "Chance", "Cleaver"};
			String[] dArr = {"Dog", "Dinner", "Damage", "Darling", "Day", "Defect", "Dentist", "Detective", "Dirt", "Door"};
			String[] eArr = {"Education", "Eclipse", "Empty", "End", "Engineer", "England", "Enormous", "Enthusiast", "Episode", "Event"};
			String[] fArr = {"Fact", "Fall", "Family", "Farewell", "Father", "Frost", "Freeze", "Fault", "Feeling", "Football"};
			String[] gArr = {"Garage", "Garden", "Gate", "Girl", "Glove", "Grant", "Grapes", "Grass", "Grill", "Gabage"};
			String[] hArr = {"Home", "House", "Hot", "Horny", "Hall", "Headmaster", "Heart", "Hedge", "Height", "Horse"};
			String[] iArr = {"Item", "Icon", "Image", "Iteration", "Issue", "Idol", "Import", "Iphone", "Illusion", "Instagram"};
			String[] jArr = {"Joke", "Jacket", "January", "Junior", "Juice", "Jam", "Jaguar", "Judge", "June", "July"};
			String[] kArr = {"King", "Kiosk", "Kitten", "Key", "Knee", "Kitchen", "Kiss", "Knife", "Kid", "Kettle"};
			String[] lArr = {"Lamp", "Lounge", "Ladder", "Leaderboard", "Leaf", "Language", "Leisure", "Lantern", "Landscape", "Library"};
			String[] mArr = {"Monkey", "Magic", "Maid", "Male", "Mail", "Medicine", "Mar", "Margin", "Malice", "Mate"};
			String[] nArr = {"Nail", "Name", "Napkin", "Natural", "Native", "Noble", "Nominee", "Noon", "Nurse", "North"};
			String[] oArr = {"Oil", "Olimpic", "Ocean", "October", "Onion", "Option", "Opportunity", "Occasion", "Original", "October"};
			String[] pArr = {"Page", "Parent", "Pain", "Paper", "Party", "Patience", "Pencil", "People", "Perfect", "Petrole"};
			String[] qArr = {"Queen", "Quick", "Quarter", "Question", "Quality", "Qualify", "Quantity", "Quote", "Queue", "Quiet"};
			String[] rArr = {"Rubbish", "Rekt", "Roar", "Reading", "Rewind", "Review", "Remember", "Rain", "Rapid", "Reflection"};
			String[] sArr = {"Sad", "Sale", "Saturday", "School", "Science", "Schedule", "Scizzors", "Sea", "Season", "Summer"};
			String[] tArr = {"Top", "Table", "Tail", "Tall", "Tea", "Teacher", "Theatre", "Ticket", "Tooth", "Tower"};
			String[] uArr = {"Umbrella", "Urban", "Unit", "Unique", "Uniform", "Ultimate", "User", "Ukraine", "Unusual", "Urgent"};
			String[] vArr = {"Vacation", "Valley", "Value", "Vegetable", "Victory", "Village", "Visible", "Vote", "Volume", "Violence"};
			String[] wArr = {"Wallet", "Wage", "Wiseman", "Warm", "Worm", "Water", "Weapon", "Weather", "Weeding", "Way"};
			String[] xArr = {"Xylophone", "Xenophobia", "Xanthipp", "Xylograph", "Xmas", "Xylanthrax", "Xenogamy", "Xanthous", "Xenon", "Xyster"};
			String[] yArr = {"Yard", "Year", "Yield", "Yawn", "Young", "Year", "Yesterday", "Youth", "Yellow", "Yarn"};
			String[] zArr = {"Zero", "Zone", "Zoom", "Zipper", "Zealous", "Zeal", "Zoo", "Zebra", "Zeppelin", "Zealotry"};
			
			if(last.equals("a")) {
					ret = (aArr[(int) rnd]);
					checkLast += aArr[(int) rnd].substring(aArr[(int) rnd].length()-1).toLowerCase();
					cnt++;	
			}
			else if(last.equals("b")) {
				ret = bArr[(int) rnd];
				checkLast += bArr[(int) rnd].substring(bArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("c")) {
				ret = (cArr[(int) rnd]);
				checkLast += cArr[(int) rnd].substring(cArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("d")) {
				ret = (dArr[(int) rnd]);
				checkLast += dArr[(int) rnd].substring(dArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("e")) {
				ret = (eArr[(int) rnd]);
				checkLast += eArr[(int) rnd].substring(eArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("f")) {
				ret = (fArr[(int) rnd]);
				checkLast += fArr[(int) rnd].substring(fArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("g")) {
				ret = (gArr[(int) rnd]);
				checkLast += gArr[(int) rnd].substring(gArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("h")) {
				ret = (hArr[(int) rnd]);
				checkLast += hArr[(int) rnd].substring(hArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("i")) {
				ret = (iArr[(int) rnd]);
				checkLast += iArr[(int) rnd].substring(iArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("j")) {
				ret = (jArr[(int) rnd]);
				checkLast += jArr[(int) rnd].substring(jArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("k")) {
				ret = (kArr[(int) rnd]);
				checkLast += kArr[(int) rnd].substring(kArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("l")) {
				ret = (lArr[(int) rnd]);
				checkLast += lArr[(int) rnd].substring(lArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("m")) {
				ret = (mArr[(int) rnd]);
				checkLast += mArr[(int) rnd].substring(mArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("n")) {
				ret = (nArr[(int) rnd]);
				checkLast += nArr[(int) rnd].substring(nArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("o")) {
				ret = (oArr[(int) rnd]);
				checkLast += oArr[(int) rnd].substring(oArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("p")) {
				ret = (pArr[(int) rnd]);
				checkLast += pArr[(int) rnd].substring(pArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("q")) {
				ret = (qArr[(int) rnd]);
				checkLast += qArr[(int) rnd].substring(qArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("r")) {
				ret = (rArr[(int) rnd]);
				checkLast += rArr[(int) rnd].substring(rArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("s")) {
				ret = (sArr[(int) rnd]);
				checkLast += sArr[(int) rnd].substring(sArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("t")) {
				ret = (tArr[(int) rnd]);
				checkLast += tArr[(int) rnd].substring(tArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("u")) {
				ret = (uArr[(int) rnd]);
				checkLast += uArr[(int) rnd].substring(uArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("v")) {
				ret = (vArr[(int) rnd]);
				checkLast += vArr[(int) rnd].substring(vArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("w")) {
				ret = (wArr[(int) rnd]);
				checkLast += wArr[(int) rnd].substring(wArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("x")) {
				ret = (xArr[(int) rnd]);
				checkLast += xArr[(int) rnd].substring(xArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("y")) {
				ret = (yArr[(int) rnd]);
				checkLast += yArr[(int) rnd].substring(yArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("z")) {
				ret = (zArr[(int) rnd]);
				checkLast += zArr[(int) rnd].substring(zArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
		}
		else {
			ret = ("Try again. Your word must start with a letter ") + checkLast.substring(checkLast.length()-1).toUpperCase();
		}
		
		}
		
		return ret;
	}
	
	
	
	//ua ua	ua ua	ua ua	ua ua	ua ua	ua ua	ua ua	ua ua	ua ua	ua ua	ua ua	ua ua	ua ua	
	public String chatting_ua(String text) {	
		readAllFiles();
		answer = "";
		gameisOver = true;
		String textCopy = text.toLowerCase();
		if(textCopy.contains("���") || textCopy.contains("���")) {
			gameisOverUa = false;
			String[] words = {"������", "˳���", "������", "����", "�������", "������", "������", "�����", "����������", "�������"};
			double a = Math.random()*10;
			long rnd = Math.round(a);
			answer = "����� � �����. �� ����� �����..." + "\n"+ words[(int) rnd];
			checkLast_ua += words[(int) rnd].substring(words[(int) rnd].length()-1).toLowerCase();
		}
		if(textCopy.equals("�����")) {
			gameisOverUa = true;
			answer = "��� ���������. �� �������: " + Integer.toString(cnt+1) + " ����";
			cnt = 0;
		}
		if (text.equals("")) {JOptionPane.showMessageDialog(null, "������ �����!");}
		else 
		{	
			if(gameisOverUa) {
			if (textCopy.contains("����")) {
				 SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z"); 
				 Date date = new Date(System.currentTimeMillis()); 
				 String str = formatter.format(date); 
				 answer = "�������� ���� �� ���: " + str;

			}
			else if(textCopy.contains("�����") || textCopy.contains("��") || textCopy.contains("�����") || textCopy.contains("����") ||
					textCopy.contains("������") || textCopy.contains("������� ���") || textCopy.contains("������� ������")) {
					answer = greeting_ua();
			}
			else if(text.equals("/help")) {
				answer = "������� �������: " + "\n"
						+ "� ���� ����������� ����� �������������� ��������� �����" + "\n"
						+ "���� - �������� ���� �� ���" + "\n"
						+ "��� - ������� � ��� ����� " + "\n"
						+ "����� - ����� � ���" +"\n"
						+ "/������� -������� ���." + "\n" 
						+ "ó�� - �������� ������������ ��� ������" + "\n"
						+ "������ - ��������� ������������ ��� ������"
						;

			}
			else if(textCopy.equals("/�������")){
				answer = "ϳ��� ������� ���, ��� ���� �����, � ��� ������� �������� �����, ��� ���������� � �����, �� ��� ���������� ����� ����, ��� � ���� ����� ���� �����, ��� ���������� �� ����� ������ �����.";

			}
			
			else if(textCopy.equals("����� �����") || textCopy.equals("����� �����")|| textCopy.equals("���� �����")) {
				answer = UA_nation();
			}
			else if(textCopy.contains("���")) {
				try {
					File sound = new File("anthem.wav");
					b = AudioSystem.getClip();
					b.open(AudioSystem.getAudioInputStream(sound));
					FloatControl gainControl = (FloatControl) b.getControl(FloatControl.Type.MASTER_GAIN);
					gainControl.setValue(0);
					b.start();
					b.loop(999);
					answer = "������� �����. ���� �� �����";
					}
					catch(Exception e) {}
			
			}
			else if(textCopy.equals("������")) {
				answer = "�������...";
				b.stop();
			}
			else 
			{
				for(int i = 0; i < questions_ua.length; i++) {
					if (textCopy.contains(questions_ua[i])) answer = answers_ua[i];
				}
				if (answer == "") {
					answer = random_ua();
				} 

			}
		}
		else {
			if(!textCopy.contains("���")) {
				answer = gaming_ua(text);
			}
			
		}

		}
		
		return answer;

	}
	
	public String gaming_ua(String text) {

		text = text.toLowerCase();
		String last = "";
		String ret = "";
		if (text.equals("/rules")){
			ret = "ϳ��� ������� ���, ��� ���� �����, � ��� ������� �������� �����, ��� ���������� � �����, �� ��� ���������� ����� ����, ��� � ���� ����� ���� �����, ��� ���������� �� ����� ������ �����.";
		}
		else {
		if(text.length() > 1) {
			last = text.substring(text.length()-1);
		}
		else {
			last = text;
		}
		
		if((text.substring(0, 1).equals(checkLast_ua.substring(checkLast_ua.length()-1)))) {
			double a = Math.random()*9;
			long rnd = Math.round(a);
			String[] aArr = {"��������", "�������", "������", "����", "����������", "�������", "���������", "��������", "�������", "���������"};
			String[] �Arr = {"������", "����������", "�����", "�����", "����� ", "������������", "���������", "������", "������", "�����"};
			String[] �Arr = {"����", "³���", "�����", "���������", "³��", "³����", "�������", "�����", "�����", "�������"};
			String[] �Arr = {"�����", "������", "������", "������", "�������", "���", "����������", "���������", "����", "�����"};
			String[] �Arr = {"���", "�����", "�����", "���", "�����", "������", "��������", "ĳ�����", "ĳ�����", "ĳ�����"};
			String[] �Arr = {"����������", "������", "������", "�����", "��������", "�������", "��������", "��������", "������", "�������"};		
			String[] �Arr = {"����", "������볺", "������", "������", "�������", "�����", "��������", "������", "�������", "�������"};
			String[] �Arr = {"Ƴ���", "�����", "������", "�����", "���", "�������", "�������", "����", "����", "����"};
			String[] �Arr = {"������", "�������", "���������", "�����", "����", "����", "������", "���������", "������", "ǳ���"};	
			String[] �Arr = {"��������", "���", "�����", "���", "�����", "������", "�����", "�����", "����", "������"};
			String[] �Arr = {"�����", "����", "������", "�������", "����", "�������", "�����", "�������", "��'�", "��������"};
			String[] �Arr = {"���", "����", "�����", "��������", "��������", "���", "����", "������", "�����", "�����"};
			String[] �Arr = {"���", "����", "�������", "����", "��", "�������", "����", "��������", "�����", "�������"};
			String[] �Arr = {"�����", "������", "��������", "������", "��������", "�����", "ʳ������", "�����", "������", "�����"};		
			String[] �Arr = {"˳���", "˳��", "������", "�����", "˳�����", "˳�", "������", "�������", "���", "�������"};
			String[] �Arr = {"����", "���", "�������", "������", "������", "̳���", "������", "����", "�������������", "��������"};	
			String[] �Arr = {"����", "����������", "�����", "ͳ�", "����", "�����", "���������", "ͳ�", "����", "������"};			
			String[] �Arr = {"����", "�������", "���", "����", "������", "�����", "��������", "����", "�������", "������"};	
			String[] �Arr = {"ϳ���", "������", "���������", "ϳ���", "�������", "���������", "������������", "�����", "�����", "���"};		
			String[] �Arr = {"����", "г�", "г�", "г���", "������", "�������", "����", "����", "�����", "����������"};
			String[] �Arr = {"�����", "�����", "�����", "���", "ѳ�'�", "����", "�����", "��������", "ѳ�", "�����"};
			String[] �Arr = {"������", "���������", "�������", "�������", "����", "���", "����", "�����", "����", "�����"};
			String[] �Arr = {"����", "���������", "�������", "�����", "������", "���", "�����", "�������", "��������", "��������"};	
			String[] �Arr = {"����", "�������", "�����", "������", "����", "������", "�����", "�����", "�������", "�������"};
			String[] �Arr = {"������", "���", "�����", "���������", "�������", "�����", "��������", "���", "�����", "����"};
			String[] �Arr = {"������", "������", "����", "��������", "�������", "������", "���", "���", "�������", "ֳ����"};
			String[] �Arr = {"�����", "�������", "�������", "�����", "������", "������", "������", "������", "׳�", "������"};	
			String[] �Arr = {"���", "�������", "����", "������", "����", "������", "����", "��������", "������", "�����"};		
			String[] �Arr = {"�����", "���������", "����", "������", "����", "������", "�����", "������", "����", "�������"};
			String[] �Arr = {"��������", "ʳ�������", "����", "������", "���������", "����������", "������", "³����", "������", "����"};
			String[] �Arr = {"����", "����", "������", "������", "�����", "����", "����", "������", "����", "�������"};
			String[] �Arr = {"������", "���", "����", "���", "���", "�����", "����", "����", "����", "�������"};
			
			
			if(last.equals("�")) {
					ret = (aArr[(int) rnd]);
					checkLast_ua += aArr[(int) rnd].substring(aArr[(int) rnd].length()-1).toLowerCase();
					cnt++;	
			}
			else if(last.equals("�")) {
				ret = �Arr[(int) rnd];
				checkLast_ua += �Arr[(int) rnd].substring(�Arr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("�")) {
				ret = (�Arr[(int) rnd]);
				checkLast_ua += �Arr[(int) rnd].substring(�Arr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("�")) {
				ret = (�Arr[(int) rnd]);
				checkLast_ua += �Arr[(int) rnd].substring(�Arr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("�")) {
				ret = (�Arr[(int) rnd]);
				checkLast_ua += �Arr[(int) rnd].substring(�Arr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("�")) {
				ret = (�Arr[(int) rnd]);
				checkLast_ua += �Arr[(int) rnd].substring(�Arr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("�")) {
				ret = (�Arr[(int) rnd]);
				checkLast_ua += �Arr[(int) rnd].substring(�Arr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("�")) {
				ret = (�Arr[(int) rnd]);
				checkLast_ua += �Arr[(int) rnd].substring(�Arr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("�")) {
				ret = (�Arr[(int) rnd]);
				checkLast_ua += �Arr[(int) rnd].substring(�Arr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("�")) {
				ret = (�Arr[(int) rnd]);
				checkLast_ua += �Arr[(int) rnd].substring(�Arr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("�")) {
				ret = (�Arr[(int) rnd]);
				checkLast_ua += �Arr[(int) rnd].substring(�Arr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("�")) {
				ret = (�Arr[(int) rnd]);
				checkLast_ua += �Arr[(int) rnd].substring(�Arr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("�")) {
				ret = (�Arr[(int) rnd]);
				checkLast_ua += �Arr[(int) rnd].substring(�Arr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("�")) {
				ret = (�Arr[(int) rnd]);
				checkLast_ua += �Arr[(int) rnd].substring(�Arr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("�")) {
				ret = (�Arr[(int) rnd]);
				checkLast_ua += �Arr[(int) rnd].substring(�Arr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("�")) {
				ret = (�Arr[(int) rnd]);
				checkLast_ua += �Arr[(int) rnd].substring(�Arr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("�")) {
				ret = (�Arr[(int) rnd]);
				checkLast_ua += �Arr[(int) rnd].substring(�Arr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("�")) {
				ret = (�Arr[(int) rnd]);
				checkLast_ua += �Arr[(int) rnd].substring(�Arr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("�")) {
				ret = (�Arr[(int) rnd]);
				checkLast_ua +=�Arr[(int) rnd].substring(�Arr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("�")) {
				ret = (�Arr[(int) rnd]);
				checkLast_ua += �Arr[(int) rnd].substring(�Arr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("�")) {
				ret = (�Arr[(int) rnd]);
				checkLast_ua += �Arr[(int) rnd].substring(�Arr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("�")) {
				ret = (�Arr[(int) rnd]);
				checkLast_ua += �Arr[(int) rnd].substring(�Arr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("�")) {
				ret = (�Arr[(int) rnd]);
				checkLast_ua += �Arr[(int) rnd].substring(�Arr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("�")) {
				ret = (�Arr[(int) rnd]);
				checkLast_ua += �Arr[(int) rnd].substring(�Arr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("�")) {
				ret = (�Arr[(int) rnd]);
				checkLast_ua += �Arr[(int) rnd].substring(�Arr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("�")) {
				ret = (�Arr[(int) rnd]);
				checkLast_ua += �Arr[(int) rnd].substring(�Arr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("�")) {
				ret = (�Arr[(int) rnd]);
				checkLast_ua += �Arr[(int) rnd].substring(�Arr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("�")) {
				ret = (�Arr[(int) rnd]);
				checkLast_ua += �Arr[(int) rnd].substring(�Arr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("�")) {
				ret = (�Arr[(int) rnd]);
				checkLast_ua += �Arr[(int) rnd].substring(�Arr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("�")) {
				ret = (�Arr[(int) rnd]);
				checkLast_ua += �Arr[(int) rnd].substring(�Arr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("�")) {
				ret = (�Arr[(int) rnd]);
				checkLast_ua += �Arr[(int) rnd].substring(�Arr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("�")) {
				ret = (�Arr[(int) rnd]);
				checkLast_ua += �Arr[(int) rnd].substring(�Arr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			
		}
		else {
			ret = ("������� �����, ��� ����� ���������� �������� �� �� ����� ") + checkLast_ua.substring(checkLast_ua.length()-1).toUpperCase();
		}
		
		}
		
		return ret;
	
	}

	public String greeting_ua() {
		double a = Math.random()*9;
		long rnd = Math.round(a);
		if(rnd == 1) return "������� :3";
		if(rnd == 2) return "�� ������, �����!";
		if(rnd == 3) return "����� ���� ������! ³���";
		if(rnd == 4) return "���-���";
		if(rnd == 5) return "���, ����� �� ��������";
		if(rnd == 6) return "������ ��, ������ ���! �����!!!!!";
		if(rnd == 7) return "����-����";
		if(rnd == 8) return "������ ����.";
		if(rnd == 9) return "������� ���";
		return "���, ����� :3";
	}

	public String random_ua(){
		double a = Math.random()*3;
		long rnd = Math.round(a);
		if(rnd == 1) return "�����, ��� � ���� �� ������!";
		if(rnd == 2) return "��� �� �� �������, ������?.";
		if(rnd == 3) return "�� ������ :(. ������ /help ��� �������� �� � ���";
		return "������� ����� :3";
	}
	public String UA_nation() {
		double a = Math.random()*3;
		long rnd = Math.round(a);
		if(rnd == 1) return "������ �����!";
		if(rnd == 2) return "����� �����! ������ �������!";
		if(rnd == 3) return "������ �����! ������� �������� �����, �����!";
		return "������ �����!!!!!!";
	}
	
}
