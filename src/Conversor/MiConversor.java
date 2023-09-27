package Conversor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MiConversor {

	private JFrame frame;
	private JTextField txt;
	private JButton btn;
	private JComboBox comboBox;
	private JLabel lbl;

	public enum Moneda {
		pesos_dolar, pesos_euros, pesos_libra, pesos_yen, pesos_won, dolar_pesos, euro_pesos, libra_pesos, yen_pesos,
		won_pesos
	}

	public double dolar = 17.54;
	public double euro = 18.62;
	public double libra = 21.49;
	public double yen = 0.12;
	public double won = 0.013;

	public double valorInput = 0.00;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MiConversor window = new MiConversor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MiConversor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		txt = new JTextField();
		txt.setBounds(10, 11, 111, 20);
		frame.getContentPane().add(txt);
		txt.setColumns(10);

		comboBox = new JComboBox<Moneda>();
		comboBox.setModel(new DefaultComboBoxModel<>(Moneda.values()));
		comboBox.setBounds(10, 43, 111, 22);
		frame.getContentPane().add(comboBox);

		// evento boton
		btn = new JButton("Convertir");
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Convertir();
			}
		});
		btn.setBounds(142, 43, 89, 23);
		frame.getContentPane().add(btn);

		lbl = new JLabel("00.00");
		lbl.setBounds(145, 14, 86, 14);
		frame.getContentPane().add(lbl);
	}

	public void Convertir() {
		if (Validar(txt.getText())) {
			Moneda moneda = (Moneda) comboBox.getSelectedItem();
			switch (moneda) {

			case pesos_dolar:
				PesosAMoneda(dolar);
				break;
			case pesos_euros:
				PesosAMoneda(euro);
				break;
			case pesos_libra:
				PesosAMoneda(libra);
				break;
			case pesos_yen:
				PesosAMoneda(yen);
				break;
			case pesos_won:
				PesosAMoneda(won);
				break;
			case dolar_pesos:
				MonedaAPesos(dolar);
				break;
			case euro_pesos:
				MonedaAPesos(euro);
				break;
			case libra_pesos:
				MonedaAPesos(libra);
				break;
			case yen_pesos:
				MonedaAPesos(yen);
				break;
			case won_pesos:
				MonedaAPesos(won);
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + moneda);
			}

		}
	}

	public void MonedaAPesos(double moneda) {
		double res = valorInput * moneda;
		lbl.setText(Redondear(res));

	}

	public void PesosAMoneda(double moneda) {
		double res = valorInput / moneda;
		lbl.setText(Redondear(res));

	}

	public String Redondear(double valor) {
		DecimalFormat df = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.HALF_UP);
		return df.format(valor);
	}

	public boolean Validar(String texto) {
		try {
			double x = Double.parseDouble(texto);
			if (x > 0)
				;
			valorInput = x;
			return true;
		} catch (NumberFormatException e) {
			lbl.setText("Solamente puede ingresar numeros!");
			return false;
		}
	}

}
