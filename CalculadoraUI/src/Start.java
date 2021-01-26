import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;

/**
 * 
 * @author Déborah Blas Muñoz
 *
 */
public class Start {

	// ATRIBUTOS
	private JFrame frmCalculadoraDbm;
	private JTextField txtResultado;
	private JButton btnC;
	private JButton btnPar;
	private JButton btn1;
	private JButton btn2;
	private JButton btn3;
	private JButton btn4;
	private JButton btn5;
	private JButton btn6;
	private JButton btn7;
	private JButton btn8;
	private JButton btn9;
	private JButton btn0;
	private JButton btnSuma;
	private JButton btnResta;
	private JButton btnMultip;
	private JButton btnDiv;
	private JButton btnIgual;
	private Integer num1 = null;
	private Integer num2 = null;
	private String operador;
	private Integer resultado;

	// MÉTODO PARA REALIZAR LAS DIFERENTES OPERACIONES.

	public Integer operacion(Integer num1, Integer num2, String operador) {
		if (num1 != null || num2 != null || operador != null) {
			if (operador == "+") {
				resultado = num1 + num2;
			} else if (operador == "-") {
				resultado = num1 - num2;
			} else if (operador == "*") {
				resultado = num1 * num2;
			} else if (operador == "/") {
				resultado = num1 / num2;
			}
		} else {
			return null;
		}
		return resultado;
	}

	// MÉTODO PARA GENERAR MENSAJE DE ERROR

	public void error() {
		txtResultado.setForeground(Color.RED);
		txtResultado.setText("Error");
		bloqueoCalculadora();
	}

	// MÉTODO PARA BLOQUEAR TODOS LOS BOTONES EXCEPTO EL "C"

	public void bloqueoCalculadora() {

		btnPar.setEnabled(false);
		btn1.setEnabled(false);
		btn2.setEnabled(false);
		btn3.setEnabled(false);
		btn4.setEnabled(false);
		btn5.setEnabled(false);
		btn6.setEnabled(false);
		btn7.setEnabled(false);
		btn8.setEnabled(false);
		btn9.setEnabled(false);
		btn0.setEnabled(false);
		btnIgual.setEnabled(false);
		btnSuma.setEnabled(false);
		btnResta.setEnabled(false);
		btnMultip.setEnabled(false);
		btnDiv.setEnabled(false);
	}

	// MÉTODO PARA DESBLOQUEAR BOTONES

	public void desbloqueoCalculadora() {
		txtResultado.setForeground(Color.BLACK);
		txtResultado.setText("");
		num1 = null;
		num2 = null;
		operador = null;
		resultado = null;
		btnPar.setEnabled(true);
		btn1.setEnabled(true);
		btn2.setEnabled(true);
		btn3.setEnabled(true);
		btn4.setEnabled(true);
		btn5.setEnabled(true);
		btn6.setEnabled(true);
		btn7.setEnabled(true);
		btn8.setEnabled(true);
		btn9.setEnabled(true);
		btn0.setEnabled(true);
		btnIgual.setEnabled(true);
		btnSuma.setEnabled(true);
		btnResta.setEnabled(true);
		btnMultip.setEnabled(true);
		btnDiv.setEnabled(true);
	}

	public void persistencia(String operacion) {
		try {
			// Apertura flujo escritura
			BufferedWriter fOperaciones = new BufferedWriter(new FileWriter("historico.txt", true));

			// Escritura en fichero
			fOperaciones.write(operacion + "\n");
			// Cierre de fichero
			fOperaciones.close();

		} catch (IOException ex) {
			Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Start window = new Start();
					window.frmCalculadoraDbm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Start() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		// DECLARACIÓN JFRAME

		frmCalculadoraDbm = new JFrame();
		frmCalculadoraDbm.setTitle("Calculadora DBM");
		frmCalculadoraDbm.setBounds(100, 100, 315, 443);
		frmCalculadoraDbm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCalculadoraDbm.getContentPane().setLayout(null);

		// DECLARACIÓN CAJA TEXTO QUE MOSTRARÁ LOS RESULTADOS

		txtResultado = new JTextField();
		txtResultado.setEditable(false);
		txtResultado.setHorizontalAlignment(SwingConstants.RIGHT);
		txtResultado.setBounds(25, 32, 249, 41);
		frmCalculadoraDbm.getContentPane().add(txtResultado);
		txtResultado.setColumns(10);

		// TAREA 1. BOTÓN C

		btnC = new JButton("C");
		btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				desbloqueoCalculadora();
			}
		});
		btnC.setBounds(26, 99, 50, 41);
		frmCalculadoraDbm.getContentPane().add(btnC);

		// TAREA 6. BOTÓN PAR

		btnPar = new JButton("Par");
		btnPar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					num1 = Integer.parseInt(txtResultado.getText());
					if (num1 % 2 == 0) {
						txtResultado.setForeground(Color.RED);
						txtResultado.setText("Par");
						String operacion = new String("Par: " + num1);
						persistencia(operacion);
					} else {
						txtResultado.setForeground(Color.RED);
						txtResultado.setText("Impar");
						String operacion = new String("Impar: " + num1);
						persistencia(operacion);
					}
					bloqueoCalculadora();
				} catch (Exception e) {
					error();
				}
			}
		});

		btnPar.setBounds(86, 99, 60, 41);
		frmCalculadoraDbm.getContentPane().add(btnPar);

		// *********** OPERADORES MATEMÁTICOS ***********

		// TAREA 2. BOTÓN SUMA

		btnSuma = new JButton("+");
		btnSuma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					num1 = Integer.parseInt(txtResultado.getText());
					txtResultado.setText("");
					setOperador("+");
				} catch (Exception e) {
					error();
				}
			}
		});
		btnSuma.setBounds(224, 164, 50, 41);
		frmCalculadoraDbm.getContentPane().add(btnSuma);

		// TAREA 3. BOTÓN RESTA

		btnResta = new JButton("-");
		btnResta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					num1 = Integer.parseInt(txtResultado.getText());
					txtResultado.setText("");
					setOperador("-");
				} catch (Exception e) {
					error();
				}
			}
		});
		btnResta.setBounds(224, 216, 50, 41);
		frmCalculadoraDbm.getContentPane().add(btnResta);

		// TAREA 4. BOTÓN MULTIPLICAR

		btnMultip = new JButton("*");
		btnMultip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					num1 = Integer.parseInt(txtResultado.getText());
					txtResultado.setText("");
					setOperador("*");
				} catch (Exception e) {
					error();
				}
			}
		});
		btnMultip.setBounds(224, 268, 50, 41);
		frmCalculadoraDbm.getContentPane().add(btnMultip);

		// TAREA 5. BOTÓN DIVIDIR

		btnDiv = new JButton("/");
		btnDiv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					num1 = Integer.parseInt(txtResultado.getText());
					txtResultado.setText("");
					setOperador("/");
				} catch (Exception e) {
					error();
				}
			}
		});
		btnDiv.setBounds(224, 338, 50, 41);
		frmCalculadoraDbm.getContentPane().add(btnDiv);

		// TAREA 7. BOTÓN IGUAL

		btnIgual = new JButton("=");
		btnIgual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					num2 = Integer.parseInt(txtResultado.getText());
					operacion(num1, num2, operador);
					if (resultado == null) {
						error();
					} else {
						String operacion = new String(num1 + " " + operador + " " + num2 + " = " + resultado);
						persistencia(operacion);
						txtResultado.setText(String.valueOf(resultado));
					}
				} catch (ArithmeticException e) {
					txtResultado.setForeground(Color.RED);
					txtResultado.setText("Indeterminado");
				} catch (Exception e) {
					error();
				}
				bloqueoCalculadora();
			}
		});
		btnIgual.setBounds(86, 338, 110, 41);
		frmCalculadoraDbm.getContentPane().add(btnIgual);

		// *********** OPERADORES NUMÉRICOS ***********

		btn1 = new JButton("1");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtResultado.setText(txtResultado.getText() + "1");
			}
		});
		btn1.setBounds(25, 164, 50, 41);
		frmCalculadoraDbm.getContentPane().add(btn1);

		btn2 = new JButton("2");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtResultado.setText(txtResultado.getText() + "2");
			}
		});
		btn2.setBounds(86, 164, 50, 41);
		frmCalculadoraDbm.getContentPane().add(btn2);

		btn3 = new JButton("3");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtResultado.setText(txtResultado.getText() + "3");
			}
		});
		btn3.setBounds(146, 164, 50, 41);
		frmCalculadoraDbm.getContentPane().add(btn3);

		btn4 = new JButton("4");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtResultado.setText(txtResultado.getText() + "4");
			}
		});
		btn4.setBounds(25, 216, 50, 41);
		frmCalculadoraDbm.getContentPane().add(btn4);

		btn5 = new JButton("5");
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtResultado.setText(txtResultado.getText() + "5");
			}
		});
		btn5.setBounds(86, 216, 50, 41);
		frmCalculadoraDbm.getContentPane().add(btn5);

		btn6 = new JButton("6");
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtResultado.setText(txtResultado.getText() + "6");
			}
		});
		btn6.setBounds(146, 216, 50, 41);
		frmCalculadoraDbm.getContentPane().add(btn6);

		btn7 = new JButton("7");
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtResultado.setText(txtResultado.getText() + "7");
			}
		});
		btn7.setBounds(25, 268, 50, 41);
		frmCalculadoraDbm.getContentPane().add(btn7);

		btn8 = new JButton("8");
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtResultado.setText(txtResultado.getText() + "8");
			}
		});
		btn8.setBounds(86, 268, 50, 41);
		frmCalculadoraDbm.getContentPane().add(btn8);

		btn9 = new JButton("9");
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtResultado.setText(txtResultado.getText() + "9");
			}
		});
		btn9.setBounds(146, 268, 50, 41);
		frmCalculadoraDbm.getContentPane().add(btn9);

		btn0 = new JButton("0");
		btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtResultado.setText(txtResultado.getText() + "0");
			}
		});
		btn0.setBounds(25, 338, 50, 41);
		frmCalculadoraDbm.getContentPane().add(btn0);

	}

	// ACCESORES VARIABLES PARA OPERACIONES MATEMÁTICAS

	public int getNum1() {
		return num1;
	}

	public void setNum1(int num1) {
		this.num1 = num1;
	}

	public int getNum2() {
		return num2;
	}

	public void setNum2(int num2) {
		this.num2 = num2;
	}

	public String getOperador() {
		return operador;
	}

	public void setOperador(String operador) {
		this.operador = operador;
	}
}
