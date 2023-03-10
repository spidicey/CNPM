package com.raven.view;

import com.raven.conection.ConnectDatabase;
import com.raven.main.Main;
import com.raven.model.Account;
import com.raven.model.ModelUser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Path2D;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

/**
 *
 * @author thinh nguyen
 */
public class PanelSlide extends javax.swing.JLayeredPane {

    /**
     * Creates new form PanelSlide
     */
    private final Animator animator;
    private float animate = 1f;
    private boolean slideLeft;
    private final PanelLogin login;
    private final PanelLoading loading;
    private Thread th;
    private MigLayout layout;
    private JFrame fram;
    private int role;
    private String userName;
    private String password;

    public void setFram(JFrame fram) {
        this.fram = fram;
    }

    public PanelSlide() {
        initComponents();
        layout = new MigLayout("inset 0", "[fill]", "[fill]");
        setLayout(layout);
        login = new PanelLogin();
        loading = new PanelLoading();
        loading.setVisible(false);
        Color color = new Color(67, 59, 132);
        setBackground(color);
        setPreferredSize(new Dimension(350, 450));
        TimingTarget target = new TimingTargetAdapter() {

            @Override
            public void begin() {
                if (slideLeft) {
                    loading.setVisible(true);
                } else {
                    login.setVisible(true);
                }
            }

            @Override
            public void timingEvent(float fraction) {
                double width = getWidth();
                animate = fraction;
                float a = easeOutQuint(fraction);
                int x = (int) (a * width);
                loading.setAnimate(slideLeft, fraction);
                layout.setComponentConstraints(loading, "pos " + x + " 0 100% 100%");
                revalidate();
                repaint();
            }

            @Override
            public void end() {
                if (slideLeft) {
                    login.setVisible(false);
                } else {
                    loading.setVisible(false);
                    loading.reset();
                }
            }
        };
        animator = new Animator(1000, target);
        animator.setResolution(0);
        add(loading, "pos 0 0 0 0, w 0!");
        add(login);
        login.addEventLogin(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!animator.isRunning()) {
                    if (login.checkUser()) {
                        showSlide(true);
                        validateLogin(login.getUserName(), login.getPassword());
                    }
                }
            }
        });
        loading.addEventContinue(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Main main = new Main();
//                    System.err.println(loading.getAcc());
                main.setAcc(loading.getAcc());
                System.out.println(main.getAcc());
                main.setVisible(true);

                fram.dispose();
            }
        });
        loading.addEventBack(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!animator.isRunning()) {
                    showSlide(false);
                }
            }

        });
    }

    private void validateLogin(String userName, String password) {
        th = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    String sql = "select * from TAI_KHOAN where UserName=? and Password=?";
                    ConnectDatabase myConnection = new ConnectDatabase();
                    Connection conn = myConnection.openConnection();
                    PreparedStatement p = conn.prepareStatement(sql);
                    p.setString(1, userName);
                    p.setString(2, password);
                    ResultSet r = p.executeQuery();
                    if (r.next()) {
                        String userName = r.getString("UserName");
                        String password = r.getString("Password");

                        role = r.getInt("IDQuyen");

                        Icon profile;
                        profile = new ImageIcon(getClass().getResource("/com/raven/icon/user.png"));
                        ModelUser data = new ModelUser(23, userName, profile);
                        Account acc = new Account(userName, password, role);
                        System.err.println(acc);
                        loading.setAcc(acc);
                        System.err.println(loading.getAcc());
                        loading.doneLogin(data);
                    } else {
                        loading.showError("UserName or Password Incorrect");
                    }
                    r.close();
                    p.close();
//                    loading.doneLogin(new ModelUser(1, "thinh", new ImageIcon(getClass().getResource("/com/raven/icon/p1.jpg"))));
                } catch (InterruptedException e) {

                } catch (Exception e) {
                    loading.showError("Error Server");
                }

            }

        });
        th.start();
    }

    public void showSlide(boolean show) {
        slideLeft = show;
        animator.start();
    }

    /**
     * This method is called from within the constructor to initialize the
     * form.WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor. // * @param grphcs
     */
    @SuppressWarnings("unchecked")
    @Override
    public void paint(Graphics grphcs) {
        super.paint(grphcs);
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int width = getWidth();
        int height = getHeight();
        float x = easeOutQuint(animate) * width;
        float y = 0;
        int centerY = height / 2;
        Path2D.Float p = new Path2D.Float();
        p.moveTo(x, y);
        p.lineTo(x, height);
        p.curveTo(x, height, easeOutBounce(animate) * width, centerY, x, y);
        g2.setColor(getBackground());
        g2.fill(p);
        g2.dispose();
    }

    private float easeOutBounce(float x) {
        float n1 = 7.5625f;
        float d1 = 2.75f;
        double v;
        if (x < 1 / d1) {
            v = n1 * x * x;
        } else if (x < 2 / d1) {
            v = n1 * (x -= 1.5 / d1) * x + 0.75;
        } else if (x < 2.5 / d1) {
            v = n1 * (x -= 2.25 / d1) * x + 0.9375;
        } else {
            v = n1 * (x -= 2.625 / d1) * x + 0.984375;
        }
        if (slideLeft) {
            return (float) (1f - v);
        } else {
            return (float) v;
        }
    }

    private float easeOutQuint(float x) {
        double v = 1 - Math.pow(1 - x, 5);
        if (slideLeft) {
            return (float) (1f - v);
        } else {
            return (float) v;
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
