package jumpgame;
import java.sql.*;
import java.text.SimpleDateFormat;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.*;
import java.util.Date;
import javax.imageio.ImageIO;

class s{
	int x;
	int y;
	s(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
class m{
	int x;
	int y;
	m(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
class l{
	int x;
	int y;
	l(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class jg extends JFrame implements KeyListener, Runnable, MouseListener{
	
	int main = 1;
	int page = 1;
	int idid =1;
	int cx =0;
	int cy = 0;
	int cweight = 1400;
	int cheight = 700;
	int cleared = 0;
	int f_width = 1400;
	int f_height = 700;
	int uix = 450;
	int uiy = 600;
	int uis = 1;
	int chx=-100;
	int ra=0;
	int chy=-100;
	int fx=-100;
	int fy=-100;
	int jd = 0;
	int mx;
	int ji=0;
	int rcx = -100;
	int rcy = -100;
	int rrcy ;
	int my;
	int mode =1;
	int jump =1;
	int ja=0;
	int maxx=1;
	int maxi =1;
	int loading = 0;
	int t = 0;
	
	Thread th;
	boolean bw = false;
	boolean ba = false;
	boolean bd = false;
	boolean bs = false;
	boolean hp = false;
	
	Toolkit tk = Toolkit.getDefaultToolkit();
	Image bar = tk.getImage("loading-bar.png");
	Image bg = tk.getImage("bg.png");
	Image small = tk.getImage("s.png");
	Image medium = tk.getImage("m.png");
	Image large = tk.getImage("l.png");
	Image p = tk.getImage("point.png");
	Image te = tk.getImage("te.png");
	Image er = tk.getImage("er.png");
	Image st = tk.getImage("st.png");
	Image ed = tk.getImage("ed.png");
	Image sete = tk.getImage("sete.png");
	Image ch = tk.getImage("ch.png");
	Image rc = tk.getImage("rocket.png");
	Image save = tk.getImage("diskette.png");
	Image help = tk.getImage("help.png");
	Image hhp = tk.getImage("hhp.png");
	Image right = tk.getImage("rr.png");
	Image left = tk.getImage("ll.png");
	Image load = tk.getImage("load.png");
	Image save1 = tk.getImage("save.png");
	Image del = tk.getImage("delete.png");
	Image gry = tk.getImage("gry.png");
	Image grr = tk.getImage("grr.png");
	Image loadbu = tk.getImage("loadbu.png");
	Image bgg = tk.getImage("bgg.png");
	Image hhhh = tk.getImage("question-mark.png");
	
	ArrayList s_list = new ArrayList();
	ArrayList m_list = new ArrayList();
	ArrayList l_list = new ArrayList();
	
	s sm;
	m me;
	l la;
	
	Image buffImage;
	Graphics buffg;
	
	jg(){
		addMouseListener(this);
		init();
		start();
		
		setTitle("jump game");
		setSize(f_width, f_height);
		
		Dimension screen = tk.getScreenSize();
		
		int f_xpos = (int)(screen.getWidth() / 2 - f_width /2);
		int f_ypos = (int)(screen.getHeight() / 2 - f_height /2);
		
		setLocation(f_xpos, f_ypos);
		setResizable(false);
		setVisible(true);
	}
	public void init() {
		
	}
	public void start() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(this);
		th = new Thread(this);
		th.start();
	}
	public void run() {
		try {
			while(true) {
				repaint();
				if(t==2) {
					ehdnaakf help = new ehdnaakf();
				}
				if(mode==2) {
					chmove();
					grav();
					if(uix>450) {
						uix-=1;
					}
					if(uix<450) {
						uix+=1;
					}
					if(uiy>800) {
						uiy-=1;
					}
					if(uiy<800) {
						uiy+=1;
					}
				}
				if(ja >=0) {
					if(ja == 8) {
						ji=0;
						chy -= ja;
						ja-=1;
					}else if(ji<6) {
						ji+=1;
						if(ji==6) {
							ji = 0;
							ja-=1;
						}
						chy -= ja;
					}
				}
				
				for(int i=0;i<s_list.size();++i) {
					sm = (s)s_list.get(i);
					if(chy>sm.y&&chy<=sm.y+8) {
						if(chx+16>sm.x&&chx+16<=sm.x+8) {
							System.out.println("stucked!");
							chy = sm.y+8;
							ja = 0;
						}
						else if(chx<=sm.x && chx+16>=sm.x+8) {
							System.out.println("stucked!");
							chy = sm.y+8;
							ja = 0;
						}
						else if(chx>=sm.x&&chx<sm.x+8) {
							System.out.println("stucked!");
							chy = sm.y+8;
							ja = 0;
						}
					}
				}
				for(int i=0;i<m_list.size();++i) {
					me = (m)m_list.get(i);
					if(chy>me.y&&chy<=me.y+16) {
						if(me.x > chx&&me.x <chx+16) {
							System.out.println("stucked!");
							chy = me.y+16;
							ja = 0;
						}
						else if(me.x+16 > chx&&me.x+16 <chx+16) {
							System.out.println("stucked!");
							chy = me.y+16;
							ja = 0;
						}
					}
				}
				for(int i=0;i<l_list.size();++i) {
					la = (l)l_list.get(i);
					if(chy>la.y&&chy<=la.y+32) {
						if(la.x>= chx&&la.x <chx+16) {
							System.out.println("stucked!");
							chy = la.y+32;
							ja = 0;
						}
						else if(la.x+32 > chx&&la.x <chx+16) {
							System.out.println("stucked!");
							chy = la.y+32;
							ja = 0;
						}
						else if(chx>la.x && chx+16<la.x+32) {
							System.out.println("stucked!");
							chy = la.y+32;
							ja = 0;
						}
					}
				}
				if(mode ==2 ) {
					if(chx>rcx-4&&chx<rcx+32) {
					if(chy>rcy+1 && chy<rcy+48) {
						mode = 3;
						ra=0;
						chx = -100;
					}
					else if(chy+16>rcy+1 && chy+16 < rcy+48){
						mode = 3;
						ra=0;
						chx = -100;
					}
				}
				else if(chx+16>rcx-4 && chx+16 <rcx+32) {
					if(chy>rcy+1 && chy<rcy+48) {
						mode = 3;
						ra=0;
						chx = -100;
					}
					else if(chy+16>rcy+1&&chy+16<rcy+48){
						mode = 3;
						ra=0;
						chx = -100;
					}
				}
				}
				
				if(mode ==3) {
					cleared +=1;
					rcy-=ra;
					ra=1;
					if(rcy<-10) {
						mode =1;uix=450;uiy=600;chx=fx;chy=fy;
						rcy = rrcy;
					}
				}
				if(chy>700) {
					chx=fx;chy=fy;
				}
				
				if(mode==9) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			        Date now = new Date();
			        String nowTime = sdf.format(now);
					System.out.println("saved!"+nowTime);
					
					
					Connection con = null;
					Statement stmt = null;
					ResultSet rs = null;
					String dburl = "jdbc:sqlite:"+"C:\\Users\\JSM\\Desktop\\eclipse\\jumpgame\\jumpdb.db";
					try{
						Class.forName("org.sqlite.JDBC");
						con = DriverManager.getConnection(dburl);
						System.out.println("connected");
						
						stmt = con.createStatement();
						rs = stmt.executeQuery("select * from info");
						idid =1;
						while (rs.next()) {
							idid = rs.getInt("id")+1;
						}
						maxx=idid;
						stmt.executeUpdate("insert into info(id,time,rcx,rrcy,fx,fy) values("+idid+",\""+nowTime+"\","+rcx+","+rrcy+","+fx+","+fy+")");
						
						for(int i=0;i<s_list.size();++i) {
							sm = (s)s_list.get(i);
							stmt.executeUpdate("insert into small(id,num,x,y) values("+idid+","+i+","+sm.x+","+sm.y+")");
						}
						for(int i=0;i<m_list.size();++i) {
							me = (m)m_list.get(i);
							stmt.executeUpdate("insert into medium(id,num,x,y) values("+idid+","+i+","+me.x+","+me.y+")");
						}
						for(int i=0;i<l_list.size();++i) {
							la = (l)l_list.get(i);
							stmt.executeUpdate("insert into large(id,num,x,y) values("+idid+","+i+","+la.x+","+la.y+")");
						}
						
						
					}
					catch(Exception e5){
						System.out.println(e5);
					}finally {
						if(con != null) {
							try {con.close();}catch(Exception e5) {}
						}
					}
					
					
					Robot robot = null;
					try {
						robot = new Robot();
					} catch (AWTException e2) {
						e2.printStackTrace();
					}
					cx = (int) this.getLocation().getX();
					cy = (int) this.getLocation().getY();
					
					Rectangle area = new Rectangle(cx+8,cy+30,cweight-16,cheight-38);
					BufferedImage bufferedImage = robot.createScreenCapture(area);
					try {
						ImageIO.write(bufferedImage, "png", new File("C:\\Users\\JSM\\Desktop\\eclipse\\jumpgame\\capture\\"+idid+"-capture.png"));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					main = idid;
					mode = 10;
				}
				t+=1;
				Thread.sleep(8);
			}
		}catch (Exception e) {}
		
	}
	public void grav() {
		int www = 0 ;
		
		for(int i=0;i<s_list.size();++i) {
			sm = (s)s_list.get(i);
			if(chy+16==sm.y) {
				if(chx+16>sm.x&&chx+16<=sm.x+8) {
					www+=1;
					
				}
				else if(chx<=sm.x && chx+16>=sm.x+8) {
					www+=1;
				}
				else if(chx>=sm.x&&chx<sm.x+8) {
					www+=1;
				}
			}
		}
		for(int i=0;i<m_list.size();++i) {
			me = (m)m_list.get(i);
			if(chy+16==me.y) {
				if(me.x >= chx&&me.x <=chx+16) {
					www+=1;
				}
				else if(me.x+16 > chx&&me.x+16 <chx+16) {
					www+=1;
				}
			}
		}
		for(int i=0;i<l_list.size();++i) {
			la = (l)l_list.get(i);
			if(chy+16==la.y) {
				if(la.x>= chx&&la.x <chx+16) {
					www+=1;
				}
				else if(la.x+32 > chx&&la.x <chx+16) {
					www+=1;
				}
				else if(chx>la.x && chx+16<la.x+32) {
					www+=1;	
				}
			}
		}
		
		if(www==0&&jd ==1) {
			if(bw==false) {
				jd=2;
			}
		}
		else if(www==0&&jd ==2&&bw==true&&jump==1){
			jump+=1;
			System.out.println("jumped, j = "+ jump);
			ja = 8;
		}
		
		
		if(www>0) {
			jump=0;
			jd=0;
			if(bw == true) {
				if(jump<2) {
					jd = 1;
					jump+=1;
					System.out.println("jumped, j = "+ jump);
					ja = 8;
				}
			}
		}
		else {
			
			int sss = 3;
			for(int i=0;i<s_list.size();++i) {
				sm = (s)s_list.get(i);
				if(chy+19>=sm.y&&chy+19<=sm.y+8) {
					if(chx+16>sm.x&&chx+16<=sm.x+8) {
						if(sss==3) {
							sss = chy+19 - sm.y;
							//System.out.println("최소="+sss+" in small "+i);
						}
						else if(sss>chy+19 - sm.y) {
							sss = chy+19 - sm.y;
							//System.out.println("최소="+sss+" in small "+i);
						}
					}
					else if(chx<=sm.x && chx+16>=sm.x+8) {
						if(sss==3) {
							sss = chy+19 - sm.y;
							//System.out.println("최소="+sss+" in small "+i);
						}
						else if(sss>chy+19 - sm.y) {
							sss = chy+19 - sm.y;
						    //System.out.println("최소="+sss+" in small "+i);
						}
					}
					else if(chx>=sm.x&&chx<sm.x+8) {
						if(sss==3) {
							sss = chy+19 - sm.y;
							//System.out.println("최소="+sss+" in small "+i);
						}
						else if(sss>chy+19 - sm.y) {
							sss = chy+19 - sm.y;
							//System.out.println("최소="+sss+" in small "+i);
						}
					}
				}
			}
			for(int i=0;i<m_list.size();++i) {
				me = (m)m_list.get(i);
				if(chy+19>=me.y&&chy+19<=me.y+16) {
					if(me.x >= chx&&me.x <=chx+16) {
						if(sss==3) {
							sss = chy+19 - me.y;
							//System.out.println("최소="+sss+" in medium "+i);
						}
						else if(sss>chy+19 - me.y) {
							sss = chy+19 - me.y;
							//System.out.println("최소="+sss+" in medium "+i);
						}
					}
					else if(me.x+16 >chx&&me.x+16 <=chx+16) {
						if(sss==3) {
							sss = chy+19 - me.y;
							//System.out.println("최소="+sss+" in medium "+i);
						}
						else if(sss>chy+19 - me.y) {
							sss = chy+19 - me.y;
							//System.out.println("최소="+sss+" in medium "+i);
						}
					}
				}
			}
			for(int i=0;i<l_list.size();++i) {
				la = (l)l_list.get(i);
				if(chy+19>=la.y&&chy+19<=la.y+32) {
					if(la.x> chx&&la.x <chx+16) {
						if(sss==3) {
							sss = chy+19 - la.y;
							//System.out.println("최소="+sss+" in large "+i);
						}
						else if(sss>chy+19 - la.y) {
							sss = chy+19 - la.y;
							//System.out.println("최소="+sss+" in large "+i);
						}
					}
					else if(la.x+32 > chx&&la.x <chx+16) {
						if(sss==3) {
							sss = chy+19 - la.y;
							//System.out.println("최소="+sss+" in large "+i);
						}
						else if(sss>chy+19 - la.y) {
							sss = chy+19 - la.y;
							//System.out.println("최소="+sss+" in large "+i);
						}
					}
					else if(chx>=la.x && chx+16<=la.x+32) {
						if(sss==3) {
							sss = chy+19 - la.y;
							//System.out.println("최소="+sss+" in large "+i);
						}
						else if(sss>chy+19 - la.y) {
							sss = chy+19 - la.y;
							//System.out.println("최소="+sss+" in large "+i);
						}
					}
				}
			}
			if(sss==3) {
				chy+=3;
			}
			else {
				if(sss==1) {
					chy+=1;
				}
				else {
					if(sss>3) {
							sss=-1;
					if(sss==0) {
						sss = 1;
					}
						}
				chy+=sss-1;}
				
			}
		}
	}
	public void chmove() {
		if(bd==true) {
			int ddd = 0;
			for(int i=0;i<s_list.size();++i) {
				sm = (s)s_list.get(i);
				if(chx+16==sm.x) {
					if(sm.y >= chy&&sm.y <chy+16) {
						ddd+=1;
					}
					else if(sm.y+8 >= chy&&sm.y+8 <=chy+16) {
						ddd+=1;
					}
				}
			}
			for(int i=0;i<m_list.size();++i) {
				me = (m)m_list.get(i);
				if(chx+16==me.x) {
					if(me.y >= chy&&me.y <chy+16) {
						ddd+=1;
					}
					else if(me.y+16 >= chy&&me.y+16 <=chy+16) {
						ddd+=1;
					}
				}
			}
			for(int i=0;i<l_list.size();++i) {
				la = (l)l_list.get(i);
				if(chx+16==la.x) {
					if(la.y >= chy&&la.y <chy+16) {
						ddd+=1;
					}
					else if(la.y+32 >= chy&&la.y+32 <=chy+16) {
						ddd+=1;
					}
					else if(chy>=la.y && chy+16<=la.y+32) {
						ddd+=1;
						
					}
				
				}
			}
			if(ddd==0) {
				chx+=1;
			}
		}
		if(ba==true) {
			int aaa = 0;
			for(int i=0;i<s_list.size();++i) {
				sm = (s)s_list.get(i);
				if(chx==sm.x+8) {
					if(sm.y >= chy&&sm.y <chy+16) {
						aaa+=1;
					}
					else if(sm.y+8 >= chy&&sm.y+8 <=chy+16) {
						aaa+=1;
					}
					
				}
			}
			for(int i=0;i<m_list.size();++i) {
				me = (m)m_list.get(i);
				if(chx==me.x+16) {
					if(me.y >= chy&&me.y <chy+16) {
						aaa+=1;
					}
					else if(me.y+16 >= chy&&me.y+16 <=chy+16) {
						aaa+=1;
					}
				}
			}
			for(int i=0;i<l_list.size();++i) {
				la = (l)l_list.get(i);
				if(chx==la.x+32) {
					if(la.y >= chy&&la.y <chy+16) {
						aaa+=1;
					}
					else if(la.y+32 >= chy&&la.y+32 <=chy+16) {
						aaa+=1;
					}
					else if(chy>=la.y && chy+16<=la.y+32) {
						aaa+=1;
					}
				}
			}
			if(aaa==0) {
				chx-=1;
			}
		}
		
	}
	public void paint(Graphics g) {
		buffImage = createImage(f_width, f_height);
		buffg = buffImage.getGraphics();
	
		update(g);
	}
	public void update(Graphics g) {
		Draw_BG();
		if(mode !=10) {
			if(mode==9) {
				rcy=rrcy;
				chx = fx;
				chy = fy;
				buffg.drawImage(rc,rcx,rcy,this);
				buffg.drawImage(ch,chx,chy,this);
			}
			else {
				
				buffg.drawImage(rc,rcx,rcy,this);
				buffg.drawImage(ch,chx,chy,this);
			}
			if(mode!=9) {
				if(cleared>0) {
					buffg.drawImage(save,8,64,this);
				}
				buffg.drawImage(loadbu,8,32,this);
				buffg.drawImage(hhhh,8,660,this);
				Draw_UI();
			}
			Draw_small();
			Draw_medium();
			Draw_large();
			if(hp == true) {
				//buffg.drawImage(help,350,175,this);
			}
		}
		else {/////////////////////////////////////////////////////////////////////////////////////////////////////////////
			buffg.drawImage(right,750,150,this);
			buffg.drawImage(left,700,150,this);
				//buffg.drawImage(save1,100,550,this);
				buffg.drawImage(load,200,550,this);
				buffg.drawImage(del,300,550,this);
				Image mainc;
				Image subc1;
				Image subc2;
				Image subc3;
				Image subc4;
				if(main - (page-1)*4 ==1) {
					buffg.drawImage(grr,695,195,this);
				}else if(main - (page-1)*4 ==2) {
					buffg.drawImage(grr,1045,195,this);
				}else if(main - (page-1)*4 ==3) {
					buffg.drawImage(grr,695,395,this);
				}else if(main - (page-1)*4 ==4) {
					buffg.drawImage(grr,1045,395,this);
				}
				try {
					mainc = ImageIO.read(new File("C:\\Users\\JSM\\Desktop\\eclipse\\jumpgame\\capture\\"+main+"-capture.png"));
					Image remainc = mainc.getScaledInstance(680, 340, Image.SCALE_SMOOTH);
					buffg.drawImage(remainc,15,200,this);
				} catch (IOException e) {
					//e.printStackTrace();
				}
				if((page-1)*4+1<=maxx) {
				try {
					subc1 = ImageIO.read(new File("C:\\Users\\JSM\\Desktop\\eclipse\\jumpgame\\capture\\"+((page-1)*4+1)+"-capture.png"));
					Image resubc1 = subc1.getScaledInstance(300, 150, Image.SCALE_SMOOTH);
					buffg.drawImage(resubc1,700,200,this);
					//System.out.println("id = "+((page-1)*4+1)+" in "+page+" page");
				} catch (IOException e) {
						//e.printStackTrace();
				}}
				if((page-1)*4+2<=maxx) {
				try {
					subc2 = ImageIO.read(new File("C:\\Users\\JSM\\Desktop\\eclipse\\jumpgame\\capture\\"+((page-1)*4+2)+"-capture.png"));
					Image resubc2 = subc2.getScaledInstance(300, 150, Image.SCALE_SMOOTH);
					buffg.drawImage(resubc2,1050,200,this);
					//System.out.println("id = "+((page-1)*4+2)+" in "+page+" page");
				} catch (IOException e) {
					//e.printStackTrace();
				}}
				if((page-1)*4+3<=maxx) {
				try {
					subc3 = ImageIO.read(new File("C:\\Users\\JSM\\Desktop\\eclipse\\jumpgame\\capture\\"+((page-1)*4+3)+"-capture.png"));
					Image resubc3 = subc3.getScaledInstance(300, 150, Image.SCALE_SMOOTH);
					buffg.drawImage(resubc3,700,400,this);
					//System.out.println("id = "+((page-1)*4+3)+" in "+page+" page");
				} catch (IOException e) {
					//e.printStackTrace();
				}}
				if((page-1)*4+4<=maxx) {
				try {
					subc4 = ImageIO.read(new File("C:\\Users\\JSM\\Desktop\\eclipse\\jumpgame\\capture\\"+((page-1)*4+4)+"-capture.png"));
					Image resubc4 = subc4.getScaledInstance(300, 150, Image.SCALE_SMOOTH);
					buffg.drawImage(resubc4,1050,400,this);
					//System.out.println("id = "+((page-1)*4+4)+" in "+page+" page");
				} catch (IOException e) {
					e.printStackTrace();
				}}		
		}
		if(loading ==1) {
			//buffg.drawImage(bar,rcx,rcy,this);
		}
		g.drawImage(buffImage,0,0,this);
	}
	public void Draw_BG() {
		buffg.clearRect(0,0,f_width,f_width);
		if(mode==10) {
			buffg.drawImage(bgg,0,0,this);
		}
		else {buffg.drawImage(bg,0,0,this);}
	}
	public void Draw_UI() {
		buffg.drawImage(te,uix,uiy,this);
		buffg.drawImage(small,uix+28,uiy+26,this);
		buffg.drawImage(te,uix+80,uiy,this);
		buffg.drawImage(medium,uix+80+25,uiy+23,this);
		buffg.drawImage(te,uix+160,uiy,this);
		buffg.drawImage(large,uix+160+17,uiy+17,this);
		buffg.drawImage(te,uix+240,uiy,this);
		buffg.drawImage(er,uix+240,uiy,this);
		buffg.drawImage(te,uix+320,uiy,this);
		buffg.drawImage(st,uix+320,uiy,this);
		buffg.drawImage(te,uix+400,uiy,this);
		buffg.drawImage(ed,uix+400+8,uiy+8,this);
		buffg.drawImage(sete,uix+((uis-1)*80),uiy,this);
		
	}
	public void keyTyped(KeyEvent e) {
		
	}

	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_1 : uis=1; break;
		case KeyEvent.VK_2 : uis=2; break;
		case KeyEvent.VK_3 : uis=3; break;
		case KeyEvent.VK_4 : uis=4; break;
		case KeyEvent.VK_5 : uis=5; break;
		case KeyEvent.VK_6 : uis=6; break;
		case KeyEvent.VK_UP : uiy-=3; break;
		case KeyEvent.VK_DOWN : uiy+=3; break;
		case KeyEvent.VK_LEFT : uix-=3; break;
		case KeyEvent.VK_RIGHT : uix+=3; break;
		
		case KeyEvent.VK_A : ba = true; break;
		case KeyEvent.VK_D : bd = true; break;
		case KeyEvent.VK_S : bs = true; break;
		case KeyEvent.VK_ENTER : mode =2; break;
		case KeyEvent.VK_BACK_SPACE : mode =1;uix=450;uiy=600;chx=fx;chy=fy;rcy = rrcy; break;
		
		case KeyEvent.VK_W : bw = true; break;

		}
	}
	
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_W : bw = false; break;
		case KeyEvent.VK_A : ba = false; break;
		case KeyEvent.VK_D : bd = false; break;
		case KeyEvent.VK_S : bs = false; break;
		case KeyEvent.VK_SLASH : hp = false; break;
		}
	}
	public void small() {
		sm = new s(mx-4, my-4);
		s_list.add(sm);
		for(int i = 0 ; i<s_list.size();++i) {
			sm = (s)s_list.get(i);}
	}
	public void medium() {
		me = new m(mx-8, my-8);
		m_list.add(me);
		for(int i = 0 ; i<m_list.size();++i) {
			me = (m)m_list.get(i);}
	}
	public void large() {
		la = new l(mx-16, my-16);
		l_list.add(la);
		for(int i = 0 ; i<l_list.size();++i) {
			la = (l)l_list.get(i);}
	}
	public void Draw_small() {
		for(int i =0 ; i<s_list.size();++i) {
			sm = (s)s_list.get(i);
			buffg.drawImage(small, sm.x,sm.y,this);
		}
	}
	public void Draw_medium() {
		for(int i =0 ; i<m_list.size();++i) {
			me = (m)m_list.get(i);
			buffg.drawImage(medium, me.x,me.y,this);
		}
	}
	public void Draw_large() {
		for(int i =0 ; i<l_list.size();++i) {
			la = (l)l_list.get(i);
			buffg.drawImage(large, la.x,la.y,this);
		}
	}
	public void erase() {
		for(int i=0;i<s_list.size();++i) {
			sm = (s)s_list.get(i);
			if(mx>=sm.x&&mx<=sm.x+8 && my>=sm.y&&my<=sm.y+8) {
				s_list.remove(i);
			}
		}
		for(int i=0;i<m_list.size();++i) {
			me = (m)m_list.get(i);
			if(mx>=me.x&&mx<=me.x+16 && my>=me.y&&my<=me.y+16) {
				m_list.remove(i);
			}
		}
		for(int i=0;i<l_list.size();++i) {
			la = (l)l_list.get(i);
			if(mx>=la.x&&mx<=la.x+32 && my>=la.y&&my<=la.y+32) {
				l_list.remove(i);
			}
		}
	}
	public void DEL() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String dburl = "jdbc:sqlite:"+"C:\\Users\\JSM\\Desktop\\eclipse\\jumpgame\\jumpdb.db";
		try{
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection(dburl);
			System.out.println("connected");
			
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from info");
			int max = 0;
			while(rs.next()) {
				max+=1;
			}
			stmt.executeUpdate("delete from info where id = "+main);
			stmt.executeUpdate("delete from small where id = "+main);
			stmt.executeUpdate("delete from medium where id = "+main);
			stmt.executeUpdate("delete from large where id = "+main);
			Path filePath = Paths.get("C:\\Users\\JSM\\Desktop\\eclipse\\jumpgame\\capture\\"+main+"-capture.png");
			Files.delete(filePath);
			System.out.println(max);
			if(max==1) {
				mode=1;
				uix=450;uiy=600;
			}
			else {
				for(int i=main+1;i<=max+1;i++) {
				stmt.executeUpdate("update info set id = "+(i-1)+" where id = "+i);
				stmt.executeUpdate("update small set id = "+(i-1)+" where id = "+i);
				stmt.executeUpdate("update medium set id = "+(i-1)+" where id = "+i);
				stmt.executeUpdate("update large set id = "+(i-1)+" where id = "+i);
				File file = new File("C:\\Users\\JSM\\Desktop\\eclipse\\jumpgame\\capture\\"+i+"-capture.png");
			    File newFile = new File("C:\\Users\\JSM\\Desktop\\eclipse\\jumpgame\\capture\\"+(i-1)+"-capture.png");
			    Boolean result = file.renameTo(newFile);
			   	}
				rs = stmt.executeQuery("select * from info");
				idid =1;
				while (rs.next()) {
					idid = rs.getInt("id")+1;
				}
				maxx=idid;
			}
			loading = 0;
		}
		catch(Exception e5){
			System.out.println(e5);
		}finally {
			if(con != null) {
				try {con.close();}catch(Exception e5) {}
			}
		}
	}
	public void LOAD() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String dburl = "jdbc:sqlite:"+"C:\\Users\\JSM\\Desktop\\eclipse\\jumpgame\\jumpdb.db";
		try{
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection(dburl);
			System.out.println("connected");
			
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from info where id = "+main);
			rcx = rs.getInt("rcx");
			rcy = rs.getInt("rrcy");
			rrcy = rs.getInt("rrcy");
			fx = rs.getInt("fx");
			chx = fx;
			fy = rs.getInt("fy");
			chy = fy;
			
			s_list = new ArrayList();
			m_list = new ArrayList();
			l_list = new ArrayList();
			rs = stmt.executeQuery("select * from small where id = "+main);
			while (rs.next()) {
				sm = new s(rs.getInt("x"),rs.getInt("y"));
				s_list.add(sm);
				for(int i = 0 ; i<s_list.size();++i) {
					sm = (s)s_list.get(i);}
			}
			rs = stmt.executeQuery("select * from medium where id = "+main);
			while (rs.next()) {
				me = new m(rs.getInt("x"),rs.getInt("y"));
				m_list.add(me);
				for(int i = 0 ; i<m_list.size();++i) {
					me = (m)m_list.get(i);}
			}
			rs = stmt.executeQuery("select * from large where id = "+main);
			while (rs.next()) {
				la = new l(rs.getInt("x"),rs.getInt("y"));
				l_list.add(la);
				for(int i = 0 ; i<l_list.size();++i) {
					la = (l)l_list.get(i);}
			}
			mode=1;
			uix=450;uiy=600;
			System.out.println("load successed");
			loading =0;
		}
		catch(Exception e5){
			System.out.println(e5);
		}finally {
			if(con != null) {
				try {con.close();}catch(Exception e5) {}
			}
		}
	}
	public void mouseClicked(MouseEvent e) {
		System.out.println(e.getX()+" "+e.getY());
		mx = e.getX();
		my = e.getY();
		if(mx>0&&mx<40&&my>64&&my<96&&mode!=10&&cleared>0) {
			loading =1;
			mode=9;
		}
		if(mx>=7&&mx<=40&&my>=660&&my<=692) {
			ques qqu = new ques();
		}
		if(mx>0&&mx<40&&my>0&&my<64) {
			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;
			String dburl = "jdbc:sqlite:"+"C:\\Users\\JSM\\Desktop\\eclipse\\jumpgame\\jumpdb.db";
			try{
				Class.forName("org.sqlite.JDBC");
				con = DriverManager.getConnection(dburl);
				System.out.println("connected");
				
				stmt = con.createStatement();
				rs = stmt.executeQuery("select * from info");
				idid =1;
				int jijk = 0;
				while (rs.next()) {
					jijk+=1;
					idid = rs.getInt("id")+1;
				}
				maxx=idid;
				if(jijk>0) {
					System.out.println(jijk+"개의 저장된 맵이 있습니다");
					main=1;
					mode=10;
				}
				else {
					Message m = new Message("저장된 맵이 없습니다");
					System.out.println("저장된 맵이 없습니다");
				}
				loading =0;
			}
			catch(Exception e5){
				System.out.println(e5);
			}finally {
				if(con != null) {
					try {con.close();}catch(Exception e5) {}
				}
			}
		}
		else if(mode==10) {
			if(mx>=702&&mx<=737&&my>=150&&my<=185) {
				if(page>1) {
					System.out.println("previous page");
					page -=1;
				}else {
					Message m = new Message("최소 페이지입니다");
				}
				
			}
			if(mx>=752&&mx<=787&&my>=150&&my<=185) {
				loading =1;
				Connection con = null;
				Statement stmt = null;
				ResultSet rs = null;
				String dburl = "jdbc:sqlite:"+"C:\\Users\\JSM\\Desktop\\eclipse\\jumpgame\\jumpdb.db";
				try{
					Class.forName("org.sqlite.JDBC");
					con = DriverManager.getConnection(dburl);
					System.out.println("connected");
					
					stmt = con.createStatement();
					rs = stmt.executeQuery("select * from info");
					maxi =1;
					while (rs.next()) {
						maxi = rs.getInt("id")+1;
					}
					if(maxi>=page*4+1) {
						System.out.println("next page");
						page+=1;
					}
					else {
						Message m = new Message("최대 페이지입니다");
					}
					
				}
				catch(Exception e5){
					System.out.println(e5);
				}finally {
					if(con != null) {
						try {con.close();}catch(Exception e5) {}
					}
				}
				
			}
			
			if(mx>=100&&mx<=195&&my>=550&&my<=595) {
				//System.out.println("save");
			}
			if(mx>=200&&mx<=295&&my>=550&&my<=595) {
				loading = 1;
				System.out.println("load");
				LOAD();
			}
			if(mx>=300&&mx<=395&&my>=550&&my<=595) {
				loading =1;
				System.out.println("del");
				DEL();
			}
			if(mx>=700&&mx<=1000&&my>=200&&my<=350) {
				System.out.println("id = "+((page-1)*4+1)+" in "+page+" page");
				main = ((page-1)*4+1);
			}
			if(mx>=1050&&mx<=1350&&my>=200&&my<=350) {
				System.out.println("id = "+((page-1)*4+2)+" in "+page+" page");
				main = ((page-1)*4+2);
			}
			if(mx>=700&&mx<=1000&&my>=400&&my<=550) {
				System.out.println("id = "+((page-1)*4+3)+" in "+page+" page");
				main = ((page-1)*4+3);
			}
			if(mx>=1050&&mx<=1350&&my>=400&&my<=550) {
				System.out.println("id = "+((page-1)*4+4)+" in "+page+" page");
				main = ((page-1)*4+4);
			}
		}
		else if(mx>=7&&mx<=40&&my>=660&&my<=692) {
			ques qqu = new ques();
		}
		else if(mode==1) {
			if(uis == 1) {
				cleared = 0;
			small();
		}
		else if(mode==1&&uis == 2) {
			cleared = 0;
			medium();
		}
		else if(mode==1&&uis == 3) {
			cleared = 0;
			large();
		}
		else if(mode==1&&uis==4) {
			cleared = 0;
			erase();
		}
		else if(mode==1&&uis == 5) {
			cleared = 0;
			chx = mx-8;
			chy = my-8;
			fx=mx-8;
			fy = my-8;
		}
		else if(mode==1&&uis == 6) {
			cleared = 0;
			rcx = mx-16;
			rcy = my-32;
			rrcy = my-32;
		}
		}
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	public void mouseEntered(MouseEvent e) {	
	}
	public void mouseExited(MouseEvent e) {
	}
	public class Message extends JFrame implements ActionListener{

	    public Message(String mes){
	        setUndecorated(true);
	        setSize(250,35);
	        setVisible(true);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        Dimension screens = tk.getScreenSize();
	    	int xx = (int)(screens.getWidth() / 2 -125);
			int yy = (int)(screens.getHeight() / 2 - 17);
			setLocation(xx, yy);
	        Font font1 = new Font("맑은 고딕", Font.BOLD, 20);
	        JButton bt = new JButton(mes);
	        bt.setBackground(new Color(255,255,255));
	        add(bt);
	        bt.setFont(font1);
	        bt.addActionListener(this);
	        setVisible(true);
	    }
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        // TODO Auto-generated method stub
	        dispose();
	    }
	}
	public class ehdnaakf extends JFrame implements ActionListener{

	    public ehdnaakf(){
	        setUndecorated(true);
	        setSize(410,150);
	        setVisible(true);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        Dimension screens = tk.getScreenSize();
	    	int xx = (int)(screens.getWidth() / 2 -200);
			int yy = (int)(screens.getHeight() / 2 - 60);
			setLocation(xx, yy);
	        Font font1 = new Font("맑은 고딕", Font.BOLD, 15);
	        JButton bt = new JButton("<html><body><center>1,2,3,4,5,6을 눌러 도구를 바꿀 수 있습니다<br>왼쪽 하단의 버튼을 눌러 도움말을 볼 수 있습니다<br>enter를 눌러 맵을 실행 할 수 있고<br>클리어하면 왼쪽 위의 저장 버튼을 눌러<br>맵을 저장을 하거나 불러올 수 있습니다<br>(클릭하여 창 닫기)</center></body></html>");
	        bt.setBackground(new Color(255,255,255));
	        bt.setSize(410,200);
	        add(bt);
	        bt.setFont(font1);
	        bt.addActionListener(this);
	        setVisible(true);
	    }
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        // TODO Auto-generated method stub
	        dispose();
	    }
	}public class ques extends JFrame implements ActionListener{

	    public ques(){
	        setUndecorated(true);
	        setSize(410,150);
	        setVisible(true);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        Dimension screens = tk.getScreenSize();
	    	int xx = (int)(screens.getWidth() / 2 -200);
			int yy = (int)(screens.getHeight() / 2 - 60);
			setLocation(xx, yy);
	        Font font1 = new Font("맑은 고딕", Font.BOLD, 15);
	        JButton bt = new JButton("<html><body><center>1 : 작은 블럭   2 : 중간 블럭<br>3 : 큰 블럭   4 : 지우개<br>5 : 시작 지점   6 : 도착 지점<br>enter : 실행<br>backspace : 실행 정지<br>(클릭하여 창 닫기)</center></body></html>");
	        bt.setBackground(new Color(255,255,255));
	        bt.setSize(410,200);
	        add(bt);
	        bt.setFont(font1);
	        bt.addActionListener(this);
	        setVisible(true);
	    }
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        // TODO Auto-generated method stub
	        dispose();
	    }
	}
}

public class maiain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		jg fms = new jg();
	}

}
