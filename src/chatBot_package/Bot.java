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
	//ініціалізація змінних
	List<String> list = new ArrayList<>();
	String[] questions; //англійські питання на які бот зможе відповісти
	String[] answers; //англійські відповіді бота
	String[] answers_ua; //укр відповіді
	String[] questions_ua; //укр питання
	String answer; //змінна, якій присвоїться відповідь бота
	
	//змінні для гри
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
		if(textCopy.contains("гра") || textCopy.contains("гру")) {
			gameisOverUa = false;
			String[] words = {"Машина", "Літак", "Аптека", "Лечо", "Курсова", "Турник", "Штанга", "Мавпа", "Переводчик", "Телефон"};
			double a = Math.random()*10;
			long rnd = Math.round(a);
			answer = "Давай я почну. Моє перше слово..." + "\n"+ words[(int) rnd];
			checkLast_ua += words[(int) rnd].substring(words[(int) rnd].length()-1).toLowerCase();
		}
		if(textCopy.equals("вихід")) {
			gameisOverUa = true;
			answer = "Гру завершено. Ви набрали: " + Integer.toString(cnt+1) + " балів";
			cnt = 0;
		}
		if (text.equals("")) {JOptionPane.showMessageDialog(null, "Введіть текст!");}
		else 
		{	
			if(gameisOverUa) {
			if (textCopy.contains("дата")) {
				 SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z"); 
				 Date date = new Date(System.currentTimeMillis()); 
				 String str = formatter.format(date); 
				 answer = "Теперішня дата та час: " + str;

			}
			else if(textCopy.contains("привіт") || textCopy.contains("ку") || textCopy.contains("салют") || textCopy.contains("вітаю") ||
					textCopy.contains("вітання") || textCopy.contains("доброго дня") || textCopy.contains("доброго вечора")) {
					answer = greeting_ua();
			}
			else if(text.equals("/help")) {
				answer = "Допоміжні команди: " + "\n"
						+ "Я можу спілкуватися тільки використовуючи простенькі фрази" + "\n"
						+ "Дата - показати дату та час" + "\n"
						+ "Гра - пограти в гру СЛОВА " + "\n"
						+ "Вихід - вийти з гри" +"\n"
						+ "/правила -правила гри." + "\n" 
						+ "Гімн - включити національний гімн України" + "\n"
						+ "Зупини - виключити національний гімн України"
						;

			}
			else if(textCopy.equals("/правила")){
				answer = "Після початку гри, бот пише слово, а Вам потрібно написати слово, яке починається з букви, на яку закінчилося слово бота, бот в свою чергу пише слово, яке починається на букву вашого слова.";

			}
			
			else if(textCopy.equals("слава україні") || textCopy.equals("слава нації")|| textCopy.equals("путін хуйло")) {
				answer = UA_nation();
			}
			else if(textCopy.contains("гімн")) {
				try {
					File sound = new File("anthem.wav");
					b = AudioSystem.getClip();
					b.open(AudioSystem.getAudioInputStream(sound));
					FloatControl gainControl = (FloatControl) b.getControl(FloatControl.Type.MASTER_GAIN);
					gainControl.setValue(0);
					b.start();
					b.loop(999);
					answer = "Пишаюсь тобою. Руку на серце";
					}
					catch(Exception e) {}
			
			}
			else if(textCopy.equals("зупини")) {
				answer = "Зупиняю...";
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
			if(!textCopy.contains("гра")) {
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
			ret = "Після початку гри, бот пише слово, а Вам потрібно написати слово, яке починається з букви, на яку закінчилося слово бота, бот в свою чергу пише слово, яке починається на букву вашого слова.";
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
			String[] aArr = {"Антилопа", "Авокадо", "Артерія", "Авто", "Амбулаторія", "Автомат", "Автошкола", "Аеропорт", "Антитіла", "Абонемент"};
			String[] бArr = {"Бронза", "Березневий", "Брова", "Брухт", "Бабай ", "Бомбосховище", "Баскетбол", "Блінчик", "Басейн", "Бабак"};
			String[] вArr = {"Вода", "Вітер", "Ворог", "Ворожнеча", "Віск", "Вівчар", "Валянок", "Вайда", "Вагон", "Ваганки"};
			String[] гArr = {"Ганок", "Гадати", "Година", "Глечик", "Гантеля", "Грім", "Гвинтокрил", "Гелікоптер", "Геній", "Герой"};
			String[] дArr = {"Двір", "Дочка", "Дурак", "Дух", "Дрова", "Дорога", "Детектив", "Дівчина", "Діалект", "Діагноз"};
			String[] еArr = {"Електричка", "Енергія", "Емоції", "Емпат", "Електрик", "Екзамен", "Економіка", "Ергономія", "Ерудит", "Емблема"};		
			String[] єArr = {"Єнот", "Євангеліє", "Єгипет", "Єдиний", "Єхидний", "Єльце", "Єрусалим", "Єретик", "Єдинчук", "Єднакий"};
			String[] жArr = {"Жінка", "Жених", "Жовтий", "Жереб", "Жах", "Жахіття", "Жменька", "Жлоб", "Жовч", "Жаба"};
			String[] зArr = {"Золото", "Зеркало", "Забиванка", "Збори", "Забір", "Забіг", "Забара", "Забаганка", "Забава", "Зірка"};	
			String[] иArr = {"Интенція", "Ино", "Имати", "Ива", "Инжир", "Иначий", "Импет", "Индик", "Инак", "Инакий"};
			String[] іArr = {"Ікона", "Ігла", "Іговка", "Іменини", "Ігра", "Іграшка", "Ідеал", "Інженер", "Ім'я", "Інститут"};
			String[] йArr = {"Йод", "Йорж", "Йорка", "Йоломчик", "Йойкотня", "Йти", "Йори", "Йойчик", "Йолом", "Йолоп"};
			String[] їArr = {"Їжа", "Їжак", "Їдальня", "Їдло", "Їр", "Їжачиха", "Їжик", "Їрований", "Їстки", "Їмельга"};
			String[] кArr = {"Клоун", "Комедія", "Кумедний", "Корона", "Кмітливий", "Козак", "Кінцевий", "Крісло", "Клепач", "Кегля"};		
			String[] лArr = {"Літак", "Літо", "Листок", "Листя", "Лінивий", "Лід", "Лазівка", "Ладянка", "Лад", "Лабіринт"};
			String[] мArr = {"Море", "Мрія", "Метелик", "Мариво", "Музика", "Місце", "Мережа", "Межа", "Марнотратство", "Медіафайл"};	
			String[] нArr = {"Небо", "Народження", "Народ", "Ніч", "Ноги", "Нерви", "Натюрморт", "Ніж", "Нора", "Небеса"};			
			String[] оArr = {"Орда", "Оркестр", "Овіс", "Оазіс", "Обабок", "Обава", "Обаясник", "Обвід", "Облачно", "Обахта"};	
			String[] пArr = {"Пітон", "Пляшки", "Перевізник", "Північ", "Перерва", "Первінство", "Передбачення", "Павич", "Павук", "Пай"};		
			String[] рArr = {"Рота", "Рік", "Річ", "Річка", "Рябина", "Рекурсія", "Радіо", "Рагу", "Радар", "Ралентандо"};
			String[] сArr = {"Смола", "Сербія", "Сумка", "Сніг", "Сім'я", "Сель", "Смерч", "Смиренно", "Сім", "Секта"};
			String[] тArr = {"Тормоз", "Табуретка", "Таможня", "Трактор", "Тигр", "Тир", "Такт", "Трійня", "Трек", "Тапок"};
			String[] уArr = {"Удав", "Унікальний", "Уміренно", "Утікач", "Убунту", "Убіч", "Увага", "Убрання", "Уведення", "Убиясник"};	
			String[] фArr = {"Факт", "Фермент", "Фарба", "Фарист", "Фаля", "Фарфур", "Фальш", "Фалда", "Фанатик", "Фантазія"};
			String[] хArr = {"Хазяїн", "Хам", "Хавдій", "Хавтурник", "Хавтура", "Халат", "Хромовий", "Хліб", "Хмара", "Хвіст"};
			String[] цArr = {"Церква", "Цикорій", "Цвях", "Церемонія", "Цвинтар", "Цемент", "Цех", "Цар", "Царство", "Ціпкий"};
			String[] чArr = {"Чабан", "Черемша", "Черешня", "Череп", "Чуткий", "Чистий", "Чоловік", "Чорний", "Чіп", "Чоботи"};	
			String[] шArr = {"Шия", "Широкий", "Швея", "Шептун", "Шкіра", "Швабра", "Шаги", "Шановний", "Шелест", "Школа"};		
			String[] щArr = {"Щастя", "Щебетання", "Щебіт", "Щезлий", "Щепій", "Щавлик", "Щезун", "Щиглик", "Щент", "Щабидуб"};
			String[] ьArr = {"Людський", "Кіньський", "Льон", "Сьомга", "Шампіньойн", "Шампанське", "Сьомий", "Вільха", "Лосьон", "Льох"};
			String[] юArr = {"Юнга", "Юнак", "Ютреня", "Юпчина", "Юрист", "Юрба", "Юрма", "Юзефок", "Юдея", "Юхварка"};
			String[] яArr = {"Яблоко", "Явір", "Язва", "Якір", "Яма", "Ябеда", "Ягня", "Яйце", "Язик", "Яворець"};
			
			
			if(last.equals("а")) {
					ret = (aArr[(int) rnd]);
					checkLast_ua += aArr[(int) rnd].substring(aArr[(int) rnd].length()-1).toLowerCase();
					cnt++;	
			}
			else if(last.equals("б")) {
				ret = бArr[(int) rnd];
				checkLast_ua += бArr[(int) rnd].substring(бArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("в")) {
				ret = (вArr[(int) rnd]);
				checkLast_ua += вArr[(int) rnd].substring(вArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("г")) {
				ret = (гArr[(int) rnd]);
				checkLast_ua += гArr[(int) rnd].substring(гArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("д")) {
				ret = (дArr[(int) rnd]);
				checkLast_ua += дArr[(int) rnd].substring(дArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("е")) {
				ret = (еArr[(int) rnd]);
				checkLast_ua += еArr[(int) rnd].substring(еArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("є")) {
				ret = (єArr[(int) rnd]);
				checkLast_ua += єArr[(int) rnd].substring(єArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("ж")) {
				ret = (жArr[(int) rnd]);
				checkLast_ua += жArr[(int) rnd].substring(жArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("з")) {
				ret = (зArr[(int) rnd]);
				checkLast_ua += зArr[(int) rnd].substring(зArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("и")) {
				ret = (иArr[(int) rnd]);
				checkLast_ua += иArr[(int) rnd].substring(иArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("і")) {
				ret = (іArr[(int) rnd]);
				checkLast_ua += іArr[(int) rnd].substring(іArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("й")) {
				ret = (йArr[(int) rnd]);
				checkLast_ua += йArr[(int) rnd].substring(йArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("ї")) {
				ret = (їArr[(int) rnd]);
				checkLast_ua += їArr[(int) rnd].substring(їArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("к")) {
				ret = (кArr[(int) rnd]);
				checkLast_ua += кArr[(int) rnd].substring(кArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("л")) {
				ret = (лArr[(int) rnd]);
				checkLast_ua += лArr[(int) rnd].substring(лArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("м")) {
				ret = (мArr[(int) rnd]);
				checkLast_ua += мArr[(int) rnd].substring(мArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("н")) {
				ret = (нArr[(int) rnd]);
				checkLast_ua += нArr[(int) rnd].substring(нArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("о")) {
				ret = (оArr[(int) rnd]);
				checkLast_ua += оArr[(int) rnd].substring(оArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("п")) {
				ret = (пArr[(int) rnd]);
				checkLast_ua +=пArr[(int) rnd].substring(пArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("р")) {
				ret = (рArr[(int) rnd]);
				checkLast_ua += рArr[(int) rnd].substring(рArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("с")) {
				ret = (сArr[(int) rnd]);
				checkLast_ua += сArr[(int) rnd].substring(сArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("т")) {
				ret = (тArr[(int) rnd]);
				checkLast_ua += тArr[(int) rnd].substring(тArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("у")) {
				ret = (уArr[(int) rnd]);
				checkLast_ua += уArr[(int) rnd].substring(уArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("ф")) {
				ret = (фArr[(int) rnd]);
				checkLast_ua += фArr[(int) rnd].substring(фArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("х")) {
				ret = (хArr[(int) rnd]);
				checkLast_ua += хArr[(int) rnd].substring(хArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("ц")) {
				ret = (цArr[(int) rnd]);
				checkLast_ua += цArr[(int) rnd].substring(цArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("ч")) {
				ret = (чArr[(int) rnd]);
				checkLast_ua += чArr[(int) rnd].substring(чArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("ш")) {
				ret = (шArr[(int) rnd]);
				checkLast_ua += шArr[(int) rnd].substring(шArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("щ")) {
				ret = (щArr[(int) rnd]);
				checkLast_ua += щArr[(int) rnd].substring(щArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("ь")) {
				ret = (ьArr[(int) rnd]);
				checkLast_ua += ьArr[(int) rnd].substring(ьArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("ю")) {
				ret = (юArr[(int) rnd]);
				checkLast_ua += юArr[(int) rnd].substring(юArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			else if(last.equals("я")) {
				ret = (яArr[(int) rnd]);
				checkLast_ua += яArr[(int) rnd].substring(яArr[(int) rnd].length()-1).toLowerCase();
				cnt++;	
			}
			
		}
		else {
			ret = ("Спробуй знову, твоє слово закінчилось почалось не на букву ") + checkLast_ua.substring(checkLast_ua.length()-1).toUpperCase();
		}
		
		}
		
		return ret;
	
	}

	public String greeting_ua() {
		double a = Math.random()*9;
		long rnd = Math.round(a);
		if(rnd == 1) return "Здорова :3";
		if(rnd == 2) return "Мої вітання, друже!";
		if(rnd == 3) return "Радий Тебе бачити! Вітаю";
		if(rnd == 4) return "Хей-хей";
		if(rnd == 5) return "Йов, давно не бачились";
		if(rnd == 6) return "Скільки літ, скільки зим! Привіт!!!!!";
		if(rnd == 7) return "Хола-хола";
		if(rnd == 8) return "Добрий день.";
		if(rnd == 9) return "Доброго дня";
		return "Хех, привіт :3";
	}

	public String random_ua(){
		double a = Math.random()*3;
		long rnd = Math.round(a);
		if(rnd == 1) return "Вибач, але я Тебе не розумію!";
		if(rnd == 2) return "Про що ти говориш, козаче?.";
		if(rnd == 3) return "Не розумію :(. Напиши /help щоб дізнатися що я вмію";
		return "Спробуй знову :3";
	}
	public String UA_nation() {
		double a = Math.random()*3;
		long rnd = Math.round(a);
		if(rnd == 1) return "Героям Слава!";
		if(rnd == 2) return "Слава Нації! Смерть ворогам!";
		if(rnd == 3) return "Героям Слава! Бандера пишається тобою, синку!";
		return "Героям Слава!!!!!!";
	}
	
}
