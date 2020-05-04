import javax.swing.*;
import java.awt.*; 
import java.text.NumberFormat;
	
public class oop extends JFrame {
	public static void main(String[] args) { new oop(); }

	private JTextField mTextPrice = new JTextField(); 
	private JTextField mTextDown = new JTextField(); 
	private JTextField mTextMonth = new JTextField(); 
	private JTextField mTextRate = new JTextField(); 
	private JTextField mTextResult = new JTextField(); 
	private JRadioButton mRadioBath, mRadioPercent; 
	private Font mFont = new Font("Tahoma", Font.PLAIN, 14);

	public oop() {
		UIManager.put("OptionPane.messageFont", mFont); //สำหรับ Message Dialog

		setTitle("อัตราการผ่อนชำระงวดรถ"); 
		setDefaultCloseOperation(WindowConstants. EXIT_ON_CLOSE);
		setLocationRelativeTo(null); 
		setSize(400, 300);
		setResizable(false);
		setLayout(new GridLayout(6, 1));
			
		JPanel p1 = new JPanel(); 
		p1.add(new JLabel(" ราคารถ ")); 
		mTextPrice.setPreferredSize(new Dimension(150, 25));
		p1.add(mTextPrice); 
		p1.add(new JLabel(" บาท")); 
		setFont(p1);
		add(p1);
		
		JPanel p2 = new JPanel();
		p2.add(new JLabel("			เงินดาวน์")); 
		mTextDown.setPreferredSize(new Dimension (150, 25));
		p2.add(mTextDown); 
		mRadioBath = new JRadioButton("บาท");
		mRadioBath.setSelected(true);
		p2.add(mRadioBath); 
		mRadioPercent = new JRadioButton("%"); 
		p2.add(mRadioPercent);
		ButtonGroup group = new ButtonGroup(); 
		group.add (mRadioBath); 
		group.add (mRadioPercent); 
		setFont(p2); 
		add(p2);
			
		JPanel p3 = new JPanel();
		p3.add(new JLabel("อัตราดอกเบี้ย"));
		mTextRate.setPreferredSize(new Dimension (150, 25)); 
		p3.add (mTextRate); p3.add(new JLabel(" % ต่อปี	 "));
		setFont(p3); 
		add(p3);
			
		JPanel p4 = new JPanel();
		p4.add(new JLabel("จำนวนงวด ")); 
		mTextMonth.setPreferredSize(new Dimension (150, 25));
		p4.add(mTextMonth); 
		p4.add(new JLabel(" เดือน ")); 
		setFont(p4); 
		add(p4);
			
		JPanel p5 = new JPanel();
		p5.add(new JLabel(" ")); 
		JButton buttonCal = new JButton("คำนวณ");
		buttonCal.addActionListener(e  -> calculate());
		p5.add(buttonCal);
		setFont(p5);
		add(p5);
		
		JPanel p6 = new JPanel();
		p6.add(new JLabel("			ค่างวด	" ));
		mTextResult.setEnabled(false);
		mTextResult.setDisabledTextColor(Color. RED);
		mTextResult.setPreferredSize(new Dimension(150, 25));
		p6.add(mTextResult);
		p6.add(new JLabel(" บาท / เดือน "));
		setFont(p6);
		add(p6);
				
		setVisible(true);
	}
	private void setFont(JPanel panel) {
		int count = panel.getComponentCount();
		for(int i = 0; i < count; i++) {
			panel.getComponent(i).setFont(mFont);
		}
	}	
	private void calculate() {
		double price = parse(mTextPrice);
		if(price <= 0) {
			showMessage("ราคารถไม่ถูกต้อง!");
			return;
		}
			
		double down = parse(mTextDown);
		if(down <= 0 || down >= price) {
			showMessage("เงินดาวน์ไม่ถูกต้อง!");
			return;
		}
				
		
		if(mRadioPercent.isSelected()) {
			down = price * (down / 100);
		}	
			
		double rate = parse(mTextRate);
		if(rate <= 0) {
			showMessage("อัตราดอกเบี้ยไม่ถูกต้อง!");
			return;
		}
		
		double ratePerMonth = (rate / 12) / 100;
		double finance = price - down;
		int months = (int)parse(mTextMonth);
		if(months <= 0) {
			showMessage("จำนวนงวดไม่ถูกต้อง!");
			return;
		}

		double interest = finance * months * ratePerMonth;
		double total = finance + interest;
		double installment = total / months;
		
		NumberFormat format = NumberFormat.getNumberInstance();
		String result = format.format(installment);
		mTextResult.setText(result);
	}
	
	private double parse(JTextField textField) { 
		try {
			return Double.parseDouble(textField.getText().trim());
		} catch(Exception ex) {
			return -1;
		}
	}
	
	private void showMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}
}