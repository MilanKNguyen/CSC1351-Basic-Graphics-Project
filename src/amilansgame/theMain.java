package amilansgame;

import basicgraphics.BasicContainer;
import basicgraphics.BasicFrame;
import basicgraphics.Clock;
import basicgraphics.SpriteComponent;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import amilansgame.ImportantUserInfo;
import basicgraphics.Sprite;
import basicgraphics.SpriteSpriteCollisionListener;
import basicgraphics.Task;
import basicgraphics.examples.BasicGraphics;
import static basicgraphics.examples.BasicGraphics.DIE_AFTER;
import basicgraphics.images.Picture;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import amilansgame.Bush1;
import amilansgame.Bush2;
import amilansgame.Person;
import basicgraphics.sounds.*;
import basicgraphics.sounds.ReusableClip;
import java.util.HashMap;
import java.util.Map;
import prog.game.ProgSpriteComponent;

/**
 *
 * @author mnguy
 */
public class theMain {

    public static Map<String, JLabel> animalStatus = new HashMap<>();
    public static Map<String, JLabel> nameStatus = new HashMap<>();
    public static Map<String, JLabel> saveCodeStatus = new HashMap<>();

//////////////////////////////////////SOUNDS
    final static ReusableClip talking = new ReusableClip("animalcrossingsound.wav");

////////////////////////////////////////// BASIC FRAME
    public static void main(String args[]) {
	final BasicFrame bf = new BasicFrame("Milan's Game");
	final Container content = bf.getContentPane();
	final CardLayout cards = new CardLayout();
	ImportantUserInfo user = new ImportantUserInfo();

	content.setLayout(cards);

	final BasicContainer bc3 = new BasicContainer();
	final BasicContainer bc9 = new BasicContainer();
	final BasicContainer bc11 = new BasicContainer();
	final BasicContainer bc12 = new BasicContainer();

////////////////////////////////////////// START SCREEN
	BasicContainer bc1 = new BasicContainer();

	BasicContainer bc10 = new BasicContainer();

	content.add(bc1,
		"Start Screen");
	String[][] startSplashLayout = {
	    {"Title"},
	    {"NameButton"},
	    {"StartButton"}
	};

	bc1.setStringLayout(startSplashLayout);

	bc1.add(
		"Title", new JLabel("Enter a username and then click start to begin playing"));
	JButton jname = new JButton("Username");
	JButton jstart = new JButton("Start Game");

	jname.addActionListener(
		new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e
	    ) {
		final JDialog jd = new JDialog(
			BasicFrame.getFrame().jf,
			"Enter your name",
			Dialog.ModalityType.APPLICATION_MODAL);
		BasicContainer bc = new BasicContainer();
		String[][] layout = {
		    {"Name", "Input"},
		    {"Submit", "Submit"}
		};
		JButton submit = new JButton("submit");
		final JTextField input = new JTextField("Milan");
		bc.setStringLayout(layout);
		bc.add("Name", new JLabel("Name"));
		bc.add("Input", input);
		bc.add("Submit", submit);
		jd.add(bc);
		submit.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
			user.setName(input.getText());
			int rc = JOptionPane.showConfirmDialog(jd, "Your name is " + user.getName() + " ?");
			System.out.println("rc=" + rc);
			if (rc == JOptionPane.OK_OPTION) {
			    System.out.println("Yes");
			    jd.dispose();

			} else if (rc == JOptionPane.OK_CANCEL_OPTION) {
			    System.out.println("Cancel");
			} else if (rc == JOptionPane.NO_OPTION) {
			    System.out.println("No");
			}
		    }
		});
		jd.pack();
		jd.setVisible(true);
	    }
	}
	);
	bc1.add(
		"NameButton", jname);
	bc1.add(
		"StartButton", jstart);

	final SpriteComponent sc = new SpriteComponent() {
	    @Override
	    public void paintBackground(Graphics g) {
		Dimension d = getSize();
		g.setColor(Color.white);
		g.fillRect(0, 0, d.width, d.height);
	    }
	};

	sc.setPreferredSize(new Dimension(800, 400)); //300 500
	jstart.addActionListener(
		new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e
	    ) {
		cards.show(content, "Character Selection");
	    }
	}
	);

////////////////////TUTORIAL HOUSE STUFF THAT NEEDS TO COME BEFORE
	final BasicContainer bc8 = new BasicContainer();

	final SpriteComponent housesc = new SpriteComponent() {
	    @Override
	    public void paintBackground(Graphics g) {
		Dimension d = getSize();
		g.setColor(Color.white);
		g.fillRect(0, 0, d.width, d.height);
	    }
	};

	housesc.setPreferredSize(new Dimension(800, 400));

	content.add(bc8,
		"Tutorial House");
	String[][] tutorialHouseLayout = {
	    {"b"},
	    {"e"}};
	bc8.setStringLayout(tutorialHouseLayout);
	bc8.add("b", housesc);
	JButton jLeave = new JButton("Leave (L)");
	bc8.add("e", jLeave);

	final Bed bed = new Bed(housesc, 0, 0);

	final Desk desk = new Desk(housesc, 300, 0);

	final Bookshelf bookshelf = new Bookshelf(housesc, 700, 150);

	final Person houseAva = new Person(housesc, 0, 300);

////////////////////////////////////////////////////////////////////TRAVEL MAP SELECTION
	content.add(bc10, "Travel Map");

	String[][] travelMapLayout = {
	    {"1", "2", "3", "4", "13"},
	    {"5", "6", "7", "8", "14"},
	    {"9", "10", "11", "12", "15"},};

	bc10.setStringLayout(travelMapLayout);
	bc10.add("3", new JLabel("Choose a place to go"));
	JButton jmap1 = new JButton("Tutorial Island");
	JButton jmap2 = new JButton("LSU");
	JButton jmap3 = new JButton("Texas");
	JButton jmap4 = new JButton("Florida");
	JButton jmap5 = new JButton("New Mexico");
	bc10.add("5", jmap1);
	bc10.add("6", jmap2);
	bc10.add("7", jmap3);
	bc10.add("8", jmap4);
	bc10.add("14", jmap5);

	jmap1.addActionListener(
		new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e
	    ) {
		cards.show(content, "Tutorial Island");
		user.setOutsideHouse(true);
		user.setinLSU(false);
		bc3.requestFocus();
		Clock.start(10);
	    }
	}
	);
	jmap2.addActionListener(
		new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e
	    ) {
		user.setinLSU(true);
		user.setOutsideHouse(false);
		cards.show(content, "LSU");
		bc11.requestFocus();
		Clock.start(10);
	    }
	}
	);

	jmap3.addActionListener(
		new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e
	    ) {
		JOptionPane.showMessageDialog(sc, "you have not unlocked this place");
	    }
	}
	);

	jmap4.addActionListener(
		new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e
	    ) {
		JOptionPane.showMessageDialog(sc, "you have not unlocked this place");
	    }
	}
	);

	jmap5.addActionListener(
		new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e
	    ) {
		JOptionPane.showMessageDialog(sc, "you have not unlocked this place");
	    }
	}
	);
/////////////////////////////////////////////LSU MAP

	final SpriteComponent lsusc = new SpriteComponent() {
	    @Override
	    public void paintBackground(Graphics g) {
		Dimension d = getSize();
		g.setColor(Color.white);
		g.fillRect(0, 0, d.width, d.height);
	    }
	};

	lsusc.setPreferredSize(new Dimension(800, 400));

	content.add(bc11,
		"LSU");
	String[][] lsulayout = {
	    {"b"}};
	bc11.setStringLayout(lsulayout);
	bc11.add("b", lsusc);

	final Lockett lockett = new Lockett(lsusc, 0, 0);
	final Car lsuCar = new Car(lsusc, 0, 300);
	final Pond pond = new Pond(lsusc, 500, 175);
	final Bush1 lsubush1 = new Bush1(lsusc, 600, 100);
	final Bush2 lsubush2 = new Bush2(lsusc, 400, 200);

	final Person lsuava = new Person(lsusc, 0, 300);

	lsusc.addSpriteSpriteCollisionListener(Person.class, Bush1.class, new SpriteSpriteCollisionListener< Person, Bush1>() {
	    boolean init = true;

	    @Override
	    public void collision(Person sp1, Bush1 sp2) {
		if (init) {
		    init = false;
		    bc11.addKeyListener(
			    new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke
			) {
			    int kp = ke.getKeyCode();
			    if (Sprite.overlap(sp1, sp2) == true && kp == KeyEvent.VK_SPACE) {

				if (user.islsuBush1Searched() == true) {
				    lsuava.setVelX(0);
				    lsuava.setVelY(0);
				    JOptionPane.showMessageDialog(sc, "you have already searched this bush!");
				} else if (user.islsuBush1Searched() == false) {
				    final JDialog jd = new JDialog(
					    BasicFrame.getFrame().jf,
					    "searching lsubush1...",
					    Dialog.ModalityType.APPLICATION_MODAL);
				    int rc = JOptionPane.showConfirmDialog(jd, "You found a squirrel! Would you like to capture it?");

				    System.out.println("rc=" + rc);
				    if (rc == JOptionPane.OK_OPTION) {
					System.out.println("Yes");
					user.setlsuBush1Searched(true);
					user.setFoundSquirrel(true);
				    } else if (rc == JOptionPane.OK_CANCEL_OPTION) {
					System.out.println("Cancel");
				    } else if (rc == JOptionPane.NO_OPTION) {
					System.out.println("No");
				    }
				}
			    }
			}
		    });
		}
	    }
	});

	lsusc.addSpriteSpriteCollisionListener(Person.class, Bush2.class, new SpriteSpriteCollisionListener< Person, Bush2>() {
	    boolean init = true;

	    @Override
	    public void collision(Person sp1, Bush2 sp2) {
		if (init) {
		    init = false;
		    bc11.addKeyListener(
			    new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke
			) {
			    int kp = ke.getKeyCode();
			    if (Sprite.overlap(sp1, sp2) == true && kp == KeyEvent.VK_SPACE) {

				if (user.islsuBush2Searched() == true) {
				    lsuava.setVelX(0);
				    lsuava.setVelY(0);
				    JOptionPane.showMessageDialog(sc, "you have already searched this bush!");
				} else if (user.islsuBush2Searched() == false) {
				    JOptionPane.showMessageDialog(sc, "you search this bush and find nothing");
				    user.setlsuBush2Searched(true);
				}
			    }
			}
		    });
		}
	    }
	});

	lsusc.addSpriteSpriteCollisionListener(Person.class, Pond.class, new SpriteSpriteCollisionListener< Person, Pond>() {
	    boolean init = true;

	    @Override
	    public void collision(Person sp1, Pond sp2) {
		if (init) {
		    init = false;
		    bc11.addKeyListener(
			    new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke
			) {
			    int kp = ke.getKeyCode();
			    if (Sprite.overlap(sp1, sp2) == true && kp == KeyEvent.VK_SPACE) {

				if (user.isPondSearched() == true) {
				    lsuava.setVelX(0);
				    lsuava.setVelY(0);
				    JOptionPane.showMessageDialog(sc, "you have already searched the pond!");
				} else if (user.isPondSearched() == false) {
				    final JDialog jd = new JDialog(
					    BasicFrame.getFrame().jf,
					    "searching pond...",
					    Dialog.ModalityType.APPLICATION_MODAL);
				    int rc = JOptionPane.showConfirmDialog(jd, "You found a fish! Would you like to capture it?");

				    System.out.println("rc=" + rc);
				    if (rc == JOptionPane.OK_OPTION) {
					System.out.println("Yes");
					user.setPondSearched(true);
					user.setFoundFish(true);
				    } else if (rc == JOptionPane.OK_CANCEL_OPTION) {
					System.out.println("Cancel");
				    } else if (rc == JOptionPane.NO_OPTION) {
					System.out.println("No");
				    }
				}
			    }
			}
		    });
		}
	    }
	});

	lsusc.addSpriteSpriteCollisionListener(Person.class, Lockett.class, new SpriteSpriteCollisionListener< Person, Lockett>() {
	    boolean init = true;

	    @Override
	    public void collision(Person sp1, Lockett sp2) {
		if (init) {
		    init = false;
		    bc11.addKeyListener(
			    new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke
			) {
			    int kp = ke.getKeyCode();
			    if (Sprite.overlap(sp1, sp2) == true && kp == KeyEvent.VK_SPACE) {
				lsuava.setVelX(0);
				lsuava.setVelY(0);
				final JDialog jd = new JDialog(
					BasicFrame.getFrame().jf,
					"entering lockett...",
					Dialog.ModalityType.APPLICATION_MODAL);
				int rc = JOptionPane.showConfirmDialog(jd, "Would you like to enter Hatcher Hall?");
				System.out.println("rc=" + rc);
				if (rc == JOptionPane.OK_OPTION) {
				    System.out.println("Yes");
				    cards.show(content, "Lockett");
				    user.setinLockett(true);
				    bc12.requestFocus();
				    jd.dispose();
				} else if (rc == JOptionPane.OK_CANCEL_OPTION) {
				    System.out.println("Cancel");
				} else if (rc == JOptionPane.NO_OPTION) {
				    System.out.println("No");
				}

			    }
			}
		    });
		}
	    }
	});

	lsusc.addSpriteSpriteCollisionListener(Person.class,
		Car.class,
		new SpriteSpriteCollisionListener< Person, Car>() {
	    boolean init = true;

	    @Override
	    public void collision(Person sp1, Car sp2
	    ) {
		if (init) {
		    init = false;
		    bc11.addKeyListener(
			    new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke
			) {
			    int kp = ke.getKeyCode();
			    if (Sprite.overlap(sp1, sp2) == true && kp == KeyEvent.VK_SPACE) {
				final JDialog jd = new JDialog(
					BasicFrame.getFrame().jf,
					"leaving the island...",
					Dialog.ModalityType.APPLICATION_MODAL);
				int rc = JOptionPane.showConfirmDialog(jd, "Would you like to leave LSU campus?");
				System.out.println("rc=" + rc);
				if (rc == JOptionPane.OK_OPTION) {
				    System.out.println("Yes");
				    cards.show(content, "Travel Map");
				    bc10.requestFocus();
				    jd.dispose();
				} else if (rc == JOptionPane.OK_CANCEL_OPTION) {
				    System.out.println("Cancel");
				} else if (rc == JOptionPane.NO_OPTION) {
				    System.out.println("No");
				}
			    }
			}
		    });
		}
	    }
	}
	);

	bc11.addKeyListener(
		new KeyAdapter() {
	    @Override
	    public void keyPressed(KeyEvent ke
	    ) {
		int kp = ke.getKeyCode();
		if (kp == KeyEvent.VK_RIGHT || kp == KeyEvent.VK_D) {
		    lsuava.setVelX(6); //2
		}
		if (kp == KeyEvent.VK_LEFT || kp == KeyEvent.VK_A) {
		    lsuava.setVelX(-6);
		}
		if (kp == KeyEvent.VK_UP || kp == KeyEvent.VK_W) {
		    lsuava.setVelY(-6);
		}
		if (kp == KeyEvent.VK_DOWN || kp == KeyEvent.VK_S) {
		    lsuava.setVelY(6); //2
		}
		if (kp == KeyEvent.VK_P && user.isFoundPetList() == true) {
		    cards.show(content, "Pet List");
		    bc9.requestFocus();
		}
	    }

	    @Override
	    public void keyReleased(KeyEvent ke
	    ) {
		lsuava.setVelX(0);
		lsuava.setVelY(0);
	    }
	}
	);
	Clock.addTask(lsusc.moveSprites());
///////////////////////////////////////////LOCKETT HALL
	final SpriteComponent lockettsc = new SpriteComponent() {
	    @Override
	    public void paintBackground(Graphics g) {
		Dimension d = getSize();
		g.setColor(Color.white);
		g.fillRect(0, 0, d.width, d.height);
	    }
	};

	lockettsc.setPreferredSize(new Dimension(800, 400));

	content.add(bc12,
		"Lockett");
	String[][] lockettlayout = {
	    {"b"},
	    {"a"}};
	bc12.setStringLayout(lockettlayout);
	bc12.add("b", lockettsc);

	final Frontdesk frontdesk = new Frontdesk(lockettsc, 150, 0);
	final Door door = new Door(lockettsc, 0, 0);
	final Plant plant = new Plant(lockettsc, 700, 250);

	final Person lockettava = new Person(lockettsc, 0, 300);
	JButton jLeavelockett = new JButton("Leave (L)");
	bc12.add("a", jLeavelockett);

	jLeavelockett.addActionListener(
		new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e
	    ) {
		cards.show(content, "LSU");
		user.setinLockett(false);
		bc11.requestFocus();
	    }
	}
	);

	lockettsc.addSpriteSpriteCollisionListener(Person.class, Plant.class, new SpriteSpriteCollisionListener< Person, Plant>() {
	    boolean init = true;

	    @Override
	    public void collision(Person sp1, Plant sp2) {
		if (init) {
		    init = false;
		    bc12.addKeyListener(
			    new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke
			) {
			    int kp = ke.getKeyCode();
			    if (Sprite.overlap(sp1, sp2) == true && kp == KeyEvent.VK_SPACE) {

				if (user.isPlantSearched() == true) {
				    lsuava.setVelX(0);
				    lsuava.setVelY(0);
				    JOptionPane.showMessageDialog(sc, "you have already searched the plant!");
				} else if (user.isPlantSearched() == false) {
				    final JDialog jd = new JDialog(
					    BasicFrame.getFrame().jf,
					    "searching plant...",
					    Dialog.ModalityType.APPLICATION_MODAL);
				    int rc = JOptionPane.showConfirmDialog(jd, "You search the plant and find a rat! Would you like to capture it?");
				    System.out.println("rc=" + rc);
				    if (rc == JOptionPane.OK_OPTION) {
					System.out.println("Yes");
					user.setPlantSearched(true);
					user.setFoundRat(true);
				    } else if (rc == JOptionPane.OK_CANCEL_OPTION) {
					System.out.println("Cancel");
				    } else if (rc == JOptionPane.NO_OPTION) {
					System.out.println("No");
				    }
				}
			    }
			}
		    });
		}
	    }
	});

	lockettsc.addSpriteSpriteCollisionListener(Person.class, Door.class, new SpriteSpriteCollisionListener< Person, Door>() {
	    boolean init = true;

	    @Override
	    public void collision(Person sp1, Door sp2) {
		if (init) {
		    init = false;
		    bc12.addKeyListener(
			    new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke
			) {
			    int kp = ke.getKeyCode();
			    if (Sprite.overlap(sp1, sp2) == true && kp == KeyEvent.VK_SPACE) {

				if (user.isDoorSearched() == true) {
				    lsuava.setVelX(0);
				    lsuava.setVelY(0);
				    JOptionPane.showMessageDialog(sc, "you have already opened this door!");
				} else if (user.isDoorSearched() == false) {
				    final JDialog jd = new JDialog(
					    BasicFrame.getFrame().jf,
					    "opening door...",
					    Dialog.ModalityType.APPLICATION_MODAL);
				    int rc = JOptionPane.showConfirmDialog(jd, "You open the door and a cockroach jumps out! Would you like to capture it?");
				    System.out.println("rc=" + rc);
				    if (rc == JOptionPane.OK_OPTION) {
					System.out.println("Yes");
					user.setDoorSearched(true);
					user.setFoundCockroach(true);
				    } else if (rc == JOptionPane.OK_CANCEL_OPTION) {
					System.out.println("Cancel");
				    } else if (rc == JOptionPane.NO_OPTION) {
					System.out.println("No");
				    }
				}
			    }
			}
		    });
		}
	    }
	});

	final BasicContainer bc13 = new BasicContainer();

	lockettsc.addSpriteSpriteCollisionListener(Person.class,
		Frontdesk.class,
		new SpriteSpriteCollisionListener< Person, Frontdesk>() {
	    boolean init = true;

	    @Override
	    public void collision(Person sp1, Frontdesk sp2
	    ) {
		if (init) {
		    init = false;
		    bc12.addKeyListener(
			    new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke
			) {
			    int kp = ke.getKeyCode();
			    if (Sprite.overlap(sp1, sp2) == true && kp == KeyEvent.VK_SPACE) {
				lockettava.setVelX(0);
				lockettava.setVelY(0);
				final JDialog jd = new JDialog(
					BasicFrame.getFrame().jf,
					"talking to frontdesk...",
					Dialog.ModalityType.APPLICATION_MODAL);
				int rc = JOptionPane.showConfirmDialog(jd, "Do you wish to talk to the front desk?");
				System.out.println("rc=" + rc);
				if (rc == JOptionPane.OK_OPTION) {
				    System.out.println("Yes");
				    cards.show(content, "Talking to frontdesk");
				    talking.play();
				    jd.dispose();
				} else if (rc == JOptionPane.OK_CANCEL_OPTION) {
				    System.out.println("Cancel");
				} else if (rc == JOptionPane.NO_OPTION) {
				    System.out.println("No");
				}
			    }
			}
		    });
		}
	    }
	}
	);

	content.add(bc13,
		"Talking to frontdesk");
	String[][] frontdesklayout1 = {
	    {"b", "c"},
	    {"e", "f"},
	    {"h", "i"},};

	bc13.setStringLayout(frontdesklayout1);
	Picture picfd = new Picture("frontdesktalk1.png");
	ImageIcon picfdimage = new ImageIcon(picfd.getImage());
	JLabel frontdesktalk1 = new JLabel(picfdimage);

	bc13.add(
		"e", frontdesktalk1);
	JButton jNexxt = new JButton("Have you seen any pets around?");

	bc13.add(
		"f", jNexxt);
	JButton Optionn2 = new JButton("I was just heading to class in the basement");
	bc13.add("i", Optionn2);
	JButton jSkkip = new JButton("Skip scene");

	bc13.add(
		"c", jSkkip);

	final BasicContainer bc14 = new BasicContainer();

	content.add(bc14,
		"Talking to frontdesk2");
	String[][] frontdesklayout2 = {
	    {"b", "c"},
	    {"e", "f"},
	    {"h", "i"},};

	bc14.setStringLayout(frontdesklayout2);
	Picture abc = new Picture("frontdesktalk2.png");
	ImageIcon bb = new ImageIcon(abc.getImage());
	JLabel frontdesktalk2 = new JLabel(bb);

	bc14.add(
		"e", frontdesktalk2);
	JButton jNextt = new JButton("Next");

	bc14.add(
		"f", jNextt);

	final BasicContainer bc15 = new BasicContainer();

	content.add(bc15,
		"Talking to frontdesk3");
	String[][] frontdesklayout3 = {
	    {"b", "c"},
	    {"e", "f"},
	    {"h", "i"},};

	bc15.setStringLayout(frontdesklayout2);
	Picture ddd = new Picture("frontdesktalk3.png");
	ImageIcon adsf = new ImageIcon(ddd.getImage());
	JLabel frontdesktalk3 = new JLabel(adsf);

	bc15.add(
		"e", frontdesktalk3);
	JButton jNexxtt = new JButton("Next");

	bc15.add(
		"f", jNexxtt);

	jNexxt.addActionListener(
		new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e
	    ) {
		cards.show(content, "Talking to frontdesk3");
		talking.play();
	    }
	}
	);
	Optionn2.addActionListener(
		new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e
	    ) {
		cards.show(content, "Talking to frontdesk2");
	    }
	}
	);
	jSkkip.addActionListener(
		new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e
	    ) {
		cards.show(content, "Lockett");
		bc12.requestFocus();
	    }
	}
	);
	jNexxtt.addActionListener(
		new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e
	    ) {
		cards.show(content, "Lockett");
		bc12.requestFocus();
	    }
	}
	);

	jNextt.addActionListener(
		new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e
	    ) {
		cards.show(content, "Lockett");
		bc12.requestFocus();
	    }
	}
	);

	bc12.addKeyListener(
		new KeyAdapter() {
	    @Override
	    public void keyPressed(KeyEvent ke
	    ) {
		int kp = ke.getKeyCode();
		if (kp == KeyEvent.VK_L) {
		    cards.show(content, "LSU");
		    user.setinLockett(false);
		    bc11.requestFocus();
		}
	    }
	}
	);

	bc12.addKeyListener(
		new KeyAdapter() {
	    @Override
	    public void keyPressed(KeyEvent ke
	    ) {
		int kp = ke.getKeyCode();
		if (kp == KeyEvent.VK_RIGHT || kp == KeyEvent.VK_D) {
		    lockettava.setVelX(6); //2
		}
		if (kp == KeyEvent.VK_LEFT || kp == KeyEvent.VK_A) {
		    lockettava.setVelX(-6);
		}
		if (kp == KeyEvent.VK_UP || kp == KeyEvent.VK_W) {
		    lockettava.setVelY(-6);
		}
		if (kp == KeyEvent.VK_DOWN || kp == KeyEvent.VK_S) {
		    lockettava.setVelY(6); //2
		}
		if (kp == KeyEvent.VK_P && user.isFoundPetList() == true) {
		    cards.show(content, "Pet List");
		    bc9.requestFocus();
		}
	    }

	    @Override
	    public void keyReleased(KeyEvent ke
	    ) {
		lockettava.setVelX(0);
		lockettava.setVelY(0);
	    }
	}
	);
	Clock.addTask(lockettsc.moveSprites());

////////////////////////////////////////// CHARACTER SELECTION
	final BasicContainer bc2 = new BasicContainer(); //creates new container for character selection

	content.add(bc2,
		"Character Selection");

	String[][] characterSelectionLayout = {
	    {"empty1", "empty2", "Title", "empty3", "empty4"},
	    {"Image1", "Image2", "Image3", "Image4", "Image5"},
	    {"Option1", "Option2", "Option3", "Option4", "Option5"},
	    {"empty5", "empty6", "empty7", "empty8", "empty9"}
	};

	bc2.setStringLayout(characterSelectionLayout);
	final Person ava = new Person(sc, 0, 0);
	bc2.add("Title", new JLabel("Choose a character"));
	JButton jcharacter1 = new JButton("Character 1");
	JButton jcharacter2 = new JButton("Character 2");
	JButton jcharacter3 = new JButton("Character 3");
	JButton jcharacter4 = new JButton("Character 4");
	JButton jcharacter5 = new JButton("Character 5");

	bc2.add("Option1", jcharacter1); //sets button in area
	bc2.add("Option2", jcharacter2); //sets button in area
	bc2.add("Option3", jcharacter3); //sets button in area
	bc2.add("Option4", jcharacter4); //sets button in area
	bc2.add("Option5", jcharacter5); //sets button in area

	Picture bald = new Picture("baldcharacter.png"); //sets a png into a variable
	ImageIcon baldImage = new ImageIcon(bald.getImage()); //puts the png variable into another varaible

	bc2.add("Image1", new JLabel(baldImage)); //sets the image onto the frame
	jcharacter1.addActionListener(
		new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e
	    ) {
		ava.setPicture(bald);
		houseAva.setPicture(bald);
		lsuava.setPicture(bald);
		lockettava.setPicture(bald);
		cards.show(content, "Tutorial Island");
		user.setOutsideHouse(true);
		bc3.requestFocus();
		Clock.start(10);
	    }
	}
	);

	Picture longhair = new Picture("longcharacter.png");
	ImageIcon image2 = new ImageIcon(longhair.getImage());

	bc2.add(
		"Image2", new JLabel(image2));
	jcharacter2.addActionListener(
		new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e
	    ) {
		ava.setPicture(longhair);
		houseAva.setPicture(longhair);
		lsuava.setPicture(longhair);
		lockettava.setPicture(longhair);
		cards.show(content, "Tutorial Island");
		user.setOutsideHouse(true);
		bc3.requestFocus();
		Clock.start(10);
	    }
	}
	);

	Picture spikey = new Picture("spikeycharacter.png");
	ImageIcon image3 = new ImageIcon(spikey.getImage());

	bc2.add(
		"Image3", new JLabel(image3));
	jcharacter3.addActionListener(
		new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e
	    ) {
		ava.setPicture(spikey);
		houseAva.setPicture(spikey);
		lsuava.setPicture(spikey);
		lockettava.setPicture(spikey);
		cards.show(content, "Tutorial Island");
		user.setOutsideHouse(true);
		bc3.requestFocus();
		Clock.start(10);
	    }
	}
	);

	Picture pony = new Picture("ponycharacter.png");
	ImageIcon image4 = new ImageIcon(pony.getImage());

	bc2.add("Image4", new JLabel(image4));
	jcharacter4.addActionListener(
		new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e
	    ) {
		ava.setPicture(pony);
		houseAva.setPicture(pony);
		lsuava.setPicture(pony);
		lockettava.setPicture(pony);
		cards.show(content, "Tutorial Island");
		user.setOutsideHouse(true);
		bc3.requestFocus();
		Clock.start(10);
	    }
	}
	);

	Picture swoosh = new Picture("swooshcharacter.png");
	ImageIcon image5 = new ImageIcon(swoosh.getImage());

	bc2.add("Image5", new JLabel(image5));
	jcharacter5.addActionListener(
		new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e
	    ) {
		ava.setPicture(swoosh);
		houseAva.setPicture(swoosh);
		lsuava.setPicture(swoosh);
		lockettava.setPicture(swoosh);
		cards.show(content, "Tutorial Island");
		user.setOutsideHouse(true);
		bc3.requestFocus();
		Clock.start(10);
	    }
	}
	);

//////////////////////////////////////////// TUTORIAL ISLAND
	content.add(bc3, "Tutorial Island");

	String[][] tutorialIslandLayout = {
	    {"SpriteHouse"},
	    {"Title"}};
	bc3.setStringLayout(tutorialIslandLayout);

	bc3.add("Title", new JLabel("Welcome to the tutorial. Use arrow keys or WASD to walk and space to perform an action. To begin, talk to Milan."));//creates title caption in specified slot
	bc3.add("SpriteHouse", sc);

	//bc3.add(sc);
	final Bush1 bush1 = new Bush1(sc, 0, 100);

	final Bush2 bush2 = new Bush2(sc, 0, 300);

	final Car tutorialCar = new Car(sc, 600, 300);

	final TutorialHouse tutorialHouse = new TutorialHouse(sc, 300, 200);

	final NpcMilan npcMilan = new NpcMilan(sc, 700, 0);

	sc.addSpriteSpriteCollisionListener(Person.class, Bush1.class, new SpriteSpriteCollisionListener< Person, Bush1>() {
	    boolean init = true;

	    @Override
	    public void collision(Person sp1, Bush1 sp2) {
		if (init) {
		    init = false;
		    bc3.addKeyListener(
			    new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke
			) {
			    int kp = ke.getKeyCode();
			    if (Sprite.overlap(sp1, sp2) == true && kp == KeyEvent.VK_SPACE) {
				if (user.isTalkedtoMilan() == false) {
				    ava.setVelX(0);
				    ava.setVelY(0);
				    JOptionPane.showMessageDialog(sc, "you must talk to Milan before searching this bush");
				} else if (user.isFoundPetList() == false) {
				    ava.setVelX(0);
				    ava.setVelY(0);
				    JOptionPane.showMessageDialog(sc, "you have to have Milan's pet list before searching this bush");
				} else if (user.isBush1Searched() == true) {
				    ava.setVelX(0);
				    ava.setVelY(0);
				    JOptionPane.showMessageDialog(sc, "you have already searched this bush!");
				} else if (user.isFoundPetList() == true) {

				    final JDialog jd = new JDialog(
					    BasicFrame.getFrame().jf,
					    "searching bush1...",
					    Dialog.ModalityType.APPLICATION_MODAL);
				    int rc = JOptionPane.showConfirmDialog(jd, "You found a dog! Would you like to capture it?");

				    System.out.println("rc=" + rc);
				    if (rc == JOptionPane.OK_OPTION) {
					System.out.println("Yes");
					user.setBush1Searched(true);
					user.setFoundDog(true);
				    } else if (rc == JOptionPane.OK_CANCEL_OPTION) {
					System.out.println("Cancel");
				    } else if (rc == JOptionPane.NO_OPTION) {
					System.out.println("No");
				    }
				}
			    }
			}
		    });
		}
	    }
	});

	sc.addSpriteSpriteCollisionListener(Person.class, Bush2.class, new SpriteSpriteCollisionListener< Person, Bush2>() {
	    boolean init = true;

	    @Override
	    public void collision(Person sp1, Bush2 sp2) {
		if (init) {
		    init = false;
		    bc3.addKeyListener(
			    new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke
			) {
			    int kp = ke.getKeyCode();
			    if (Sprite.overlap(sp1, sp2) == true && kp == KeyEvent.VK_SPACE) {
				if (user.isTalkedtoMilan() == false) {
				    ava.setVelX(0);
				    ava.setVelY(0);
				    JOptionPane.showMessageDialog(sc, "you must talk to Milan before searching this bush");
				} else if (user.isFoundPetList() == false) {
				    ava.setVelX(0);
				    ava.setVelY(0);
				    JOptionPane.showMessageDialog(sc, "you have to have Milan's pet list before searching this bush");
				} else if (user.isBush2Searched() == true) {
				    ava.setVelX(0);
				    ava.setVelY(0);
				    JOptionPane.showMessageDialog(sc, "you have already searched this bush!");
				} else if (user.isFoundPetList() == true) {

				    final JDialog jd = new JDialog(
					    BasicFrame.getFrame().jf,
					    "searching bush2...",
					    Dialog.ModalityType.APPLICATION_MODAL);
				    int rc = JOptionPane.showConfirmDialog(jd, "You found a cat! Would you like to capture it?");

				    System.out.println("rc=" + rc);
				    if (rc == JOptionPane.OK_OPTION) {
					System.out.println("Yes");
					user.setBush2Searched(true);
					user.setFoundCat(true);

				    } else if (rc == JOptionPane.OK_CANCEL_OPTION) {
					System.out.println("Cancel");
				    } else if (rc == JOptionPane.NO_OPTION) {
					System.out.println("No");
				    }
				}
			    }
			}
		    });
		}
	    }
	}
	);

	sc.addSpriteSpriteCollisionListener(Person.class,
		TutorialHouse.class,
		new SpriteSpriteCollisionListener< Person, TutorialHouse>() {
	    boolean init = true;

	    @Override
	    public void collision(Person sp1, TutorialHouse sp2
	    ) {
		if (init) {
		    init = false;
		    bc3.addKeyListener(
			    new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke
			) {
			    int kp = ke.getKeyCode();
			    if (Sprite.overlap(sp1, sp2) == true && kp == KeyEvent.VK_SPACE) {
				if (user.isTalkedtoMilan() == false) {
				    ava.setVelX(0);
				    ava.setVelY(0);
				    JOptionPane.showMessageDialog(sc, "you do not have a key to enter this house");
				} else if (user.isTalkedtoMilan() == true) { //show new card layout
				    final JDialog jd = new JDialog(
					    BasicFrame.getFrame().jf,
					    "unlocking the door...",
					    Dialog.ModalityType.APPLICATION_MODAL);
				    int rc = JOptionPane.showConfirmDialog(jd, "Enter Milan's home?");
				    System.out.println("rc=" + rc);
				    if (rc == JOptionPane.OK_OPTION) {
					System.out.println("Yes");
					cards.show(content, "Tutorial House");
					user.setOutsideHouse(false);
					user.setInsideHouse(true);
					bc8.requestFocus();
					jd.dispose();

				    } else if (rc == JOptionPane.OK_CANCEL_OPTION) {
					System.out.println("Cancel");
				    } else if (rc == JOptionPane.NO_OPTION) {
					System.out.println("No");
				    }

				}
			    }
			}
		    });
		}
	    }
	}
	);

	sc.addSpriteSpriteCollisionListener(Person.class,
		Car.class,
		new SpriteSpriteCollisionListener< Person, Car>() {
	    boolean init = true;

	    @Override
	    public void collision(Person sp1, Car sp2
	    ) {
		if (init) {
		    init = false;
		    bc3.addKeyListener(
			    new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke
			) {
			    int kp = ke.getKeyCode();
			    if (Sprite.overlap(sp1, sp2) == true && kp == KeyEvent.VK_SPACE) {
				if (user.isTalkedtoMilan() == true && user.isFoundPetList() == true && (user.isBush2Searched() == false || user.isBush2Searched() == false)) {
				    ava.setVelX(0);
				    ava.setVelY(0);
				    JOptionPane.showMessageDialog(sc, "you cannot leave the island until you have searched the bushes");
				} else if (user.isBush1Searched() == true && user.isBush2Searched() == true) { //i think this is the problem...
				    final JDialog jd = new JDialog(
					    BasicFrame.getFrame().jf,
					    "leaving the island...",
					    Dialog.ModalityType.APPLICATION_MODAL);
				    int rc = JOptionPane.showConfirmDialog(jd, "Would you like to leave the island?");
				    System.out.println("rc=" + rc);
				    if (rc == JOptionPane.OK_OPTION) {
					System.out.println("Yes");
					cards.show(content, "Travel Map");
					bc10.requestFocus();
					jd.dispose();

				    } else if (rc == JOptionPane.OK_CANCEL_OPTION) {
					System.out.println("Cancel");
				    } else if (rc == JOptionPane.NO_OPTION) {
					System.out.println("No");
				    }
				} else if (user.isTalkedtoMilan() == false || user.isFoundPetList() == false || user.isBush1Searched() == false || user.isBush2Searched() == false) {
				    ava.setVelX(0);
				    ava.setVelY(0);
				    JOptionPane.showMessageDialog(sc, "you cannot leave the island");
				}

			    }
			}
		    });
		}
	    }
	}
	);

	bc3.addKeyListener(
		new KeyAdapter() {
	    @Override
	    public void keyPressed(KeyEvent ke
	    ) {
		int kp = ke.getKeyCode();
		if (kp == KeyEvent.VK_RIGHT || kp == KeyEvent.VK_D) {
		    ava.setVelX(6); //2
		}
		if (kp == KeyEvent.VK_LEFT || kp == KeyEvent.VK_A) {
		    ava.setVelX(-6);
		}
		if (kp == KeyEvent.VK_UP || kp == KeyEvent.VK_W) {
		    ava.setVelY(-6);
		}
		if (kp == KeyEvent.VK_DOWN || kp == KeyEvent.VK_S) {
		    ava.setVelY(6); //2
		}
		if (kp == KeyEvent.VK_P && user.isFoundPetList() == true) {
		    cards.show(content, "Pet List");
		    bc9.requestFocus();
		}
	    }

	    @Override
	    public void keyReleased(KeyEvent ke
	    ) {
		ava.setVelX(0);
		ava.setVelY(0);
	    }
	}
	);
	Clock.addTask(sc.moveSprites());

///////////////////////////////////////PET LIST MAPS
	Map<String, String> saveCodes = new HashMap<>();
	saveCodes.put("hungryhowies", "cat");
	saveCodes.put("thelionking", "dog");
	saveCodes.put("clashofclans", "fish");
	saveCodes.put("candybytwice", "cockroach");
	saveCodes.put("smoothieking", "squirrel");
	saveCodes.put("rat", "stuartlittle");

	animalStatus.put("cat", new JLabel("????"));
	animalStatus.put("dog", new JLabel("????"));
	animalStatus.put("fish", new JLabel("????"));
	animalStatus.put("cockroach", new JLabel("????"));
	animalStatus.put("squirrel", new JLabel("????"));
	animalStatus.put("rat", new JLabel("????"));

	nameStatus.put("cat", new JLabel("????"));
	nameStatus.put("dog", new JLabel("????"));
	nameStatus.put("fish", new JLabel("????"));
	nameStatus.put("cockroach", new JLabel("????"));
	nameStatus.put("squirrel", new JLabel("????"));
	nameStatus.put("rat", new JLabel("????"));

	saveCodeStatus.put("cat", new JLabel("????"));
	saveCodeStatus.put("dog", new JLabel("????"));
	saveCodeStatus.put("fish", new JLabel("????"));
	saveCodeStatus.put("cockroach", new JLabel("????"));
	saveCodeStatus.put("squirrel", new JLabel("????"));
	saveCodeStatus.put("rat", new JLabel("????"));

///////////////////////////////////////PET LIST
	content.add(bc9,
		"Pet List");
	String[][] petListLayout = {
	    {"b", "c", "a", "o"},
	    {"e", "f", "k", "d"},
	    {"h", "i", "l", "g"},
	    {"j", "m", "n", "p"},
	    {"t", "s", "r", "q"},
	    {"u", "v", "w", "x"},
	    {"z", "1", "2", "3"},
	    {"4", "5", "6", "7"},
	    {"8", "9", "10", "11"},};

	bc9.setStringLayout(petListLayout);
	bc9.add("c", new JLabel("MILAN'S PET LIST"));
	bc9.add("e", new JLabel("Pet:"));
	bc9.add("f", new JLabel("Name:"));
	bc9.add("k", new JLabel("Save Code:"));

	bc9.add("h", animalStatus.get("cat"));
	bc9.add("i", nameStatus.get("cat")); //Yoomie
	bc9.add("l", saveCodeStatus.get("cat")); //hungryhowies

	bc9.add("j", animalStatus.get("dog"));
	bc9.add("m", nameStatus.get("dog")); //
	bc9.add("n", saveCodeStatus.get("dog")); //thelionking

	bc9.add("t", animalStatus.get("fish"));
	bc9.add("s", nameStatus.get("fish"));
	bc9.add("r", saveCodeStatus.get("fish"));

	bc9.add("u", animalStatus.get("squirrel"));
	bc9.add("v", nameStatus.get("squirrel"));
	bc9.add("w", saveCodeStatus.get("squirrel"));

	bc9.add("z", animalStatus.get("cockroach"));
	bc9.add("1", nameStatus.get("cockroach"));
	bc9.add("2", saveCodeStatus.get("cockroach"));

	bc9.add("4", animalStatus.get("rat"));
	bc9.add("5", nameStatus.get("rat"));
	bc9.add("6", saveCodeStatus.get("rat"));
//

	JButton jInstructions = new JButton("Save Code Instructions");
	bc9.add("o", jInstructions);
	JButton jSaveCode = new JButton("Save Codes");
	bc9.add("d", jSaveCode);
	JButton jClose = new JButton("Close (p)");
	bc9.add("g", jClose);

	jInstructions.addActionListener(
		new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e
	    ) {
		JOptionPane.showMessageDialog(sc, "Write down the save codes to pets you have found\nthe next time you log in,"
			+ " you can enter the codes to start where you left off"); // sc might be a problem here
	    }
	}
	);
	jClose.addActionListener(
		new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e
	    ) {
		if (user.isInsideHouse() == true) {
		    cards.show(content, "Tutorial House");
		    bc8.requestFocus();
		} else if (user.isOutsideHouse()
			== true) {
		    cards.show(content, "Tutorial Island");
		    bc3.requestFocus();
		} else if (user.isinLSU() == true) {
		    cards.show(content, "LSU");
		    bc11.requestFocus();
		}
	    }
	}
	);

	bc9.addKeyListener(
		new KeyAdapter() {
	    @Override
	    public void keyPressed(KeyEvent ke
	    ) {
		int kp = ke.getKeyCode();
		if (kp == KeyEvent.VK_P && user.isInsideHouse() == true) {
		    cards.show(content, "Tutorial House");
		    bc8.requestFocus();
		} else if (kp == KeyEvent.VK_P && user.isOutsideHouse() == true) {
		    cards.show(content, "Tutorial Island");
		    bc3.requestFocus();
		} else if (kp == KeyEvent.VK_P && user.isinLockett() == true) {
		    cards.show(content, "Lockett");
		    bc12.requestFocus();
		} else if (kp == KeyEvent.VK_P && user.isinLSU() == true) {
		    cards.show(content, "LSU");
		    bc11.requestFocus();
		}
	    }
	}
	);

	jSaveCode.addActionListener(
		new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e
	    ) {
		final JDialog jd = new JDialog(
			BasicFrame.getFrame().jf,
			"Enter save code",
			Dialog.ModalityType.APPLICATION_MODAL);
		BasicContainer bc = new BasicContainer();
		String[][] layout = {
		    {"prompt", "Input"},
		    {"Submit", "Submit"}
		};
		JButton submit = new JButton("submit");
		final JTextField input = new JTextField("enter save code here");
		bc.setStringLayout(layout);
		bc.add("prompt", new JLabel("Save code:"));
		bc.add("Input", input);
		bc.add("Submit", submit);
		jd.add(bc);
		submit.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
			if (saveCodes.containsKey(input.getText())) {

			    JOptionPane.showMessageDialog(sc, "you have unlocked " + saveCodes.get(input.getText()));
			    jd.dispose();
			    if (saveCodes.get(input.getText()).equals("cat")) {
				user.setFoundCat(true);
			    }
			    if (saveCodes.get(input.getText()).equals("dog")) {
				user.setFoundDog(true);
			    }
			    if (saveCodes.get(input.getText()).equals("fish")) {
				user.setFoundFish(true);
			    }
			    if (saveCodes.get(input.getText()).equals("squirrel")) {
				user.setFoundSquirrel(true);
			    }
			    if (saveCodes.get(input.getText()).equals("cockroach")) {
				user.setFoundCockroach(true);
			    }
			} else {
			    JOptionPane.showMessageDialog(sc, "that is not a valid save code");
			}

		    }
		});
		jd.pack();
		jd.setVisible(true);
	    }

	}
	);
	// }

///////////////////////////////////////TALKING TO MILAN
	final BasicContainer bc4 = new BasicContainer();

	sc.addSpriteSpriteCollisionListener(Person.class,
		NpcMilan.class,
		new SpriteSpriteCollisionListener< Person, NpcMilan>() {
	    boolean init = true;

	    @Override
	    public void collision(Person sp1, NpcMilan sp2
	    ) {
		if (init) {
		    init = false;
		    bc3.addKeyListener(
			    new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke
			) {
			    int kp = ke.getKeyCode();
			    if (Sprite.overlap(sp1, sp2) == true && kp == KeyEvent.VK_SPACE && user.isTalkedtoMilan() == false) {
				ava.setVelX(0);
				ava.setVelY(0);
				final JDialog jd = new JDialog(
					BasicFrame.getFrame().jf,
					"talking to Milan...",
					Dialog.ModalityType.APPLICATION_MODAL);
				int rc = JOptionPane.showConfirmDialog(jd, "Do you wish to talk to Milan?");
				System.out.println("rc=" + rc);
				if (rc == JOptionPane.OK_OPTION) {
				    System.out.println("Yes");
				    cards.show(content, "Talking to Milan");
				    talking.play();
				    jd.dispose();

				} else if (rc == JOptionPane.OK_CANCEL_OPTION) {
				    System.out.println("Cancel");
				} else if (rc == JOptionPane.NO_OPTION) {
				    System.out.println("No");
				}
			    }
			    if (Sprite.overlap(sp1, sp2) == true && kp == KeyEvent.VK_SPACE && user.isTalkedtoMilan() == true) {
				ava.setVelX(0);
				ava.setVelY(0);
				final JDialog jd = new JDialog(
					BasicFrame.getFrame().jf,
					"talking to Milan...",
					Dialog.ModalityType.APPLICATION_MODAL);
				int rc = JOptionPane.showConfirmDialog(jd, "You have already talked to Milan, would you like to replay");
				System.out.println("rc=" + rc);
				if (rc == JOptionPane.OK_OPTION) {
				    System.out.println("Yes");
				    cards.show(content, "Talking to Milan");
				    talking.play();
				    jd.dispose();

				} else if (rc == JOptionPane.OK_CANCEL_OPTION) {
				    System.out.println("Cancel");
				} else if (rc == JOptionPane.NO_OPTION) {
				    System.out.println("No");
				}
			    }
			}
		    });
		}
	    }
	}
	);

	content.add(bc4,
		"Talking to Milan");
	String[][] npcMilanLayout1 = {
	    {"b", "c"},
	    {"e", "f"},
	    {"h", "i"},};

	bc4.setStringLayout(npcMilanLayout1);
	Picture picmilantalking1 = new Picture("npcmilantalk1.png");
	ImageIcon imagemilantalking1 = new ImageIcon(picmilantalking1.getImage());
	JLabel milantalking1 = new JLabel(imagemilantalking1);

	bc4.add(
		"e", milantalking1);
	JButton jNext = new JButton("Next");

	bc4.add(
		"i", jNext);
	JButton jSkip = new JButton("Skip scene");

	bc4.add(
		"c", jSkip);

	final BasicContainer bc5 = new BasicContainer();

	content.add(bc5,
		"Talking to Milan pt2");
	String[][] npcMilanLayout2 = {
	    {"b", "c"},
	    {"e", "f"},
	    {"h", "i"},};

	bc5.setStringLayout(npcMilanLayout2);
	Picture picmilantalking2 = new Picture("npcmilantalk2.png");
	ImageIcon imagemilantalking2 = new ImageIcon(picmilantalking2.getImage());
	JLabel milantalking2 = new JLabel(imagemilantalking2);

	bc5.add(
		"e", milantalking2);
	JButton jOption1 = new JButton("Yes, I will help you");

	bc5.add(
		"f", jOption1);
	JButton jOption2 = new JButton("No, I will not help you");

	bc5.add(
		"i", jOption2);
	JButton jSkip2 = new JButton("Skip scene");

	bc5.add(
		"c", jSkip2);

	final BasicContainer bc6 = new BasicContainer();

	content.add(bc6,
		"Talking to Milan pt3");
	String[][] npcMilanLayout3 = {
	    {"b", "c"},
	    {"e", "f"},
	    {"h", "i"},};

	bc6.setStringLayout(npcMilanLayout2);
	Picture picmilantalking3 = new Picture("npcmilantalk3.png");
	ImageIcon imagemilantalking3 = new ImageIcon(picmilantalking3.getImage());
	JLabel milantalking3 = new JLabel(imagemilantalking3);

	bc6.add(
		"e", milantalking3);
	JButton jNext2 = new JButton("Next");

	bc6.add(
		"f", jNext2);
	JButton jSkip3 = new JButton("Skip scene");

	bc6.add(
		"c", jSkip3);

	final BasicContainer bc7 = new BasicContainer();

	content.add(bc7,
		"Talking to Milan pt4");
	String[][] npcMilanLayout4 = {
	    {"b", "c"},
	    {"e", "f"},
	    {"h", "i"},};

	bc7.setStringLayout(npcMilanLayout4);
	Picture picmilantalking4 = new Picture("npcmilantalk4.png");
	ImageIcon imagemilantalking4 = new ImageIcon(picmilantalking4.getImage());
	JLabel milantalking4 = new JLabel(imagemilantalking4);

	bc7.add(
		"e", milantalking4);
	JButton jOption3 = new JButton("Yes");

	bc7.add(
		"f", jOption3);
	JButton jSkip4 = new JButton("Skip scene");

	bc7.add(
		"c", jSkip4);

	jNext.addActionListener(
		new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e
	    ) {
		cards.show(content, "Talking to Milan pt2");
		talking.play();
	    }
	}
	);
	jOption1.addActionListener(
		new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e
	    ) {
		cards.show(content, "Talking to Milan pt3");
		talking.play();
	    }
	}
	);
	jNext2.addActionListener(
		new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e
	    ) {
		cards.show(content, "Tutorial Island");
		JOptionPane.showMessageDialog(sc, "You have received a key from Milan");
		user.setTalkedtoMilan(true);
		bc3.requestFocus();
	    }
	}
	);
	jOption2.addActionListener(
		new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e
	    ) {
		cards.show(content, "Talking to Milan pt4");
	    }
	}
	);
	jOption3.addActionListener(
		new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e
	    ) {
		cards.show(content, "Talking to Milan pt3");
	    }
	}
	);

	jSkip.addActionListener(
		new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e
	    ) {
		cards.show(content, "Tutorial Island");
		JOptionPane.showMessageDialog(sc, "You have received a key from Milan");
		user.setTalkedtoMilan(true);
		bc3.requestFocus();
	    }
	}
	);
	jSkip2.addActionListener(
		new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e
	    ) {
		cards.show(content, "Tutorial Island");
		JOptionPane.showMessageDialog(sc, "You have received a key from Milan");
		user.setTalkedtoMilan(true);
		bc3.requestFocus();
	    }
	}
	);
	jSkip3.addActionListener(
		new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e
	    ) {
		cards.show(content, "Tutorial Island");
		JOptionPane.showMessageDialog(sc, "You have received a key from Milan");
		user.setTalkedtoMilan(true);
		bc3.requestFocus();
	    }
	}
	);
	jSkip4.addActionListener(
		new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e
	    ) {
		cards.show(content, "Tutorial Island");
		JOptionPane.showMessageDialog(sc, "You have received a key from Milan");
		user.setTalkedtoMilan(true);
		bc3.requestFocus();
	    }
	}
	);
///////////////////////////////////////////TUTORIAL HOUSE

	housesc.addSpriteSpriteCollisionListener(Bed.class,
		Person.class,
		new SpriteSpriteCollisionListener< Bed, Person>() {
	    boolean init = true;

	    @Override
	    public void collision(Bed sp1, Person sp2
	    ) {
		if (init) {
		    init = false;
		    bc8.addKeyListener(
			    new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke
			) {
			    int kp = ke.getKeyCode();
			    if (Sprite.overlap(sp1, sp2) == true && kp == KeyEvent.VK_SPACE) {
				houseAva.setVelX(0);
				houseAva.setVelY(0);
				JOptionPane.showMessageDialog(housesc, "you take a nap");
			    }
			}

		    });
		}
	    }
	}
	);

	housesc.addSpriteSpriteCollisionListener(Person.class,
		Desk.class,
		new SpriteSpriteCollisionListener< Person, Desk>() {
	    boolean init = true;

	    @Override
	    public void collision(Person sp1, Desk sp2
	    ) {
		if (init) {
		    init = false;
		    bc8.addKeyListener(
			    new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke
			) {
			    int kp = ke.getKeyCode();
			    if (Sprite.overlap(sp1, sp2) == true && kp == KeyEvent.VK_SPACE) {
				houseAva.setVelX(0);
				houseAva.setVelY(0);
				JOptionPane.showMessageDialog(housesc, "you waste time playing video games");
			    }
			}
		    });
		}
	    }
	}
	);

	housesc.addSpriteSpriteCollisionListener(Person.class,
		Bookshelf.class,
		new SpriteSpriteCollisionListener< Person, Bookshelf>() {
	    boolean init = true;

	    @Override
	    public void collision(Person sp1, Bookshelf sp2
	    ) {
		if (init) {
		    init = false;
		    bc8.addKeyListener(
			    new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke
			) {
			    int kp = ke.getKeyCode();
			    if (Sprite.overlap(sp1, sp2) == true && kp == KeyEvent.VK_SPACE) {
				houseAva.setVelX(0);
				houseAva.setVelY(0);
				JOptionPane.showMessageDialog(housesc, "you search the bookshelf and find the list of Milan's pets \npress p to view the list");
				user.setFoundPetList(true);
			    }
			}
		    });
		}
	    }
	}
	);

	jLeave.addActionListener(
		new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e
	    ) {
		cards.show(content, "Tutorial Island");
		user.setInsideHouse(false);
		user.setOutsideHouse(true);
		bc3.requestFocus();
	    }
	}
	);

	bc8.addKeyListener(
		new KeyAdapter() {
	    @Override
	    public void keyPressed(KeyEvent ke
	    ) {
		int kp = ke.getKeyCode();
		if (kp == KeyEvent.VK_L && user.isInsideHouse() == true) {
		    cards.show(content, "Tutorial Island");
		    user.setInsideHouse(false);
		    user.setOutsideHouse(true);
		    bc3.requestFocus();
		}
	    }
	}
	);

	bc8.addKeyListener(
		new KeyAdapter() {
	    @Override
	    public void keyPressed(KeyEvent ke
	    ) {
		int kp = ke.getKeyCode();
		if (kp == KeyEvent.VK_RIGHT || kp == KeyEvent.VK_D) {
		    houseAva.setVelX(6); //2
		}
		if (kp == KeyEvent.VK_LEFT || kp == KeyEvent.VK_A) {
		    houseAva.setVelX(-6);
		}
		if (kp == KeyEvent.VK_UP || kp == KeyEvent.VK_W) {
		    houseAva.setVelY(-6);
		}
		if (kp == KeyEvent.VK_DOWN || kp == KeyEvent.VK_S) {
		    houseAva.setVelY(6); //2
		}
		if (kp == KeyEvent.VK_P && user.isFoundPetList() == true) {
		    cards.show(content, "Pet List");
		    bc9.requestFocus();
		}
	    }

	    @Override
	    public void keyReleased(KeyEvent ke
	    ) {
		houseAva.setVelX(0);
		houseAva.setVelY(0);
	    }
	}
	);
	Clock.addTask(housesc.moveSprites());

///////////////////////////////////////////// ENDING
	bf.show();

    }
}
