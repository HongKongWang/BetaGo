import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class betaGo {
	static table zs,s,ys,z,m,y,zx,x,yx;//棋盤上的九個方格
	static JFrame f;
	static table[] TA;//儲存方格的數組
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {} 
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension sc=kit.getScreenSize();
		f=new JFrame("");
		int fx=(sc.width-500)/2;
		int fy=(sc.height-400)/2;
		f.setBounds(fx,fy,500,400);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLayout(new GridLayout(3,3,5,5));
		TA=new table[]{zs,s,ys,z,m,y,zx,x,yx};
		for(int i=0;i<9;i++){
			TA[i]=new table();
		}
		f.setVisible(true);
	}
	static void Check(boolean afterComputer){
		int[][] INT={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
		//所有能連成一排的三個方格的序列數組
		for(int i=0;i<8;i++){
			if(TA[INT[i][0]].ch==TA[INT[i][1]].ch&&TA[INT[i][1]].ch==TA[INT[i][2]].ch&&TA[INT[i][2]].ch!=0){
				//如果一排被同種棋子擺滿
				for(int k=0;k<9;k++){
					TA[k].jb.setEnabled(false);
				}
				if(TA[INT[i][2]].ch==1){
					if(JOptionPane.showConfirmDialog(null, "玩家勝利!再玩一局?", "戰況",1)==0){
						for(int k=0;k<9;k++){
							TA[k].F5();
						}
					}
					break;
				}else if(TA[INT[i][2]].ch==2){
					if(JOptionPane.showConfirmDialog(null, "電腦勝利!再玩一局?", "戰況",1)==0){
						for(int k=0;k<9;k++){
							TA[k].F5();
						}
					}
					break;
				}
			}
		}
		int j=0;
		for(int k=0;k<9;k++){
			if(TA[k].ch!=0)j++;
		}
		if(j==9&&JOptionPane.showConfirmDialog(null, "平局!再玩一局?", "戰況",1)==0){
			for(int k=0;k<9;k++){
				TA[k].F5();
			}
			return;
		}else if(!afterComputer){
			think();
			Check(true);
			return;
		}
	}
	static void think(){//這是電腦要思考的內容，還沒有寫完
		int[][] INT={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
		for(int i=0;i<8;i++){
			//如果一排上已有兩個同樣的棋，那麼填充剩下一的個格
			if(TA[INT[i][0]].ch==TA[INT[i][1]].ch&&TA[INT[i][0]].ch!=0&&TA[INT[i][2]].ch==0){
				TA[INT[i][2]].click(2);
				break;
			}else if(TA[INT[i][1]].ch==TA[INT[i][2]].ch&&TA[INT[i][1]].ch!=0&&TA[INT[i][0]].ch==0){
				TA[INT[i][0]].click(2);
				break;
			}else if(TA[INT[i][2]].ch==TA[INT[i][0]].ch&&TA[INT[i][2]].ch!=0&&TA[INT[i][1]].ch==0){
				TA[INT[i][1]].click(2);
				break;
			}
			Check(true);
		}
	}
}
class table{
	//方格的類
	JButton jb;
	int ch;
	table(){
		ch=0;
		jb=new JButton();betaGo.f.add(jb);
		jb.setFont(new Font("DialogInput",0,50));
		jb.addActionListener(
	    	new ActionListener(){
	    		public void actionPerformed(ActionEvent e) {
	    			click(1);
	    			betaGo.Check(false);
	    		}
	    	}
	    );
	}
	void click(int c){
		jb.setEnabled(false);
		if(c==1)jb.setText("X");
		else if(c==2)jb.setText("O");
		ch=c;
	}
	void F5(){//刷新
		jb.setText(null);
		jb.setEnabled(true);
		ch=0;
	}
}
