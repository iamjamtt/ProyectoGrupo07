package com.example.proyectogrupo07;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.text.TextWatcher;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.widget.Toast;


import java.util.Calendar;

public class PagoActivity extends AppCompatActivity {

    private EditText editCardNumber, editExpirationDate, editCvv;
    private Button btnPay, btnPickDate;
    private TextWatcher cardNumberTextWatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago);

        editCardNumber = findViewById(R.id.editCardNumber);
        editExpirationDate = findViewById(R.id.editExpirationDate);
        editCvv = findViewById(R.id.editCvv);
        btnPay = findViewById(R.id.btnPay);
        btnPickDate = findViewById(R.id.btnPickDate);


        // Inicializar el TextWatcher
        cardNumberTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                formatCardNumber(s);
            }
        };

        // Agregar el TextWatcher al EditText de editCardNumber
        editCardNumber.addTextChangedListener(cardNumberTextWatcher);


        // Agregar listener para mostrar el DatePickerDialog al hacer clic en el botón
        btnPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
    }
    private void formatCardNumber(Editable s) {
        // Eliminar guiones y espacios en blanco del número de tarjeta
        String cardNumber = s.toString().replace("-", "").replace(" ", "");

        // Formatear el número de tarjeta con guiones después de cada bloque de 4 dígitos
        StringBuilder formatted = new StringBuilder();
        int len = cardNumber.length();
        for (int i = 0; i < len; i++) {
            if (i > 0 && i % 4 == 0) {
                formatted.append("-");
            }
            formatted.append(cardNumber.charAt(i));
        }

        // Establecer el texto formateado en el EditText
        editCardNumber.removeTextChangedListener(cardNumberTextWatcher);
        editCardNumber.setText(formatted.toString());
        editCardNumber.setSelection(formatted.length());
        editCardNumber.addTextChangedListener(cardNumberTextWatcher);
    }

    // Método para mostrar el DatePickerDialog
    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Actualizar el campo de fecha de vencimiento con la fecha seleccionada
                        String formattedDate = String.format("%02d/%02d", month + 1, year % 100);
                        editExpirationDate.setText(formattedDate);
                    }
                },
                year,
                month,
                1
        );
        // Configurar el DatePicker para mostrar solo el spinner de mes y año
        datePickerDialog.getDatePicker().setCalendarViewShown(false);
        datePickerDialog.getDatePicker().setSpinnersShown(true);

        // Mostrar el DatePickerDialog
        datePickerDialog.show();

    btnPay.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Obtener los valores de los campos
            String cardNumber = editCardNumber.getText().toString().trim();
            String expirationDate = editExpirationDate.getText().toString().trim();
            String cvv = editCvv.getText().toString().trim();

            // Validar campos
            if (TextUtils.isEmpty(cardNumber)) {
                editCardNumber.setError("Ingresa el número de tarjeta");
                return;
            }

            if (!isValidCardNumber(cardNumber)) {
                editCardNumber.setError("Número de tarjeta inválido");
                return;
            }

            if (TextUtils.isEmpty(expirationDate)) {
                editExpirationDate.setError("Ingresa la fecha de vencimiento");
                return;
            }

            if (!isValidExpirationDate(expirationDate)) {
                editExpirationDate.setError("Fecha de vencimiento inválida");
                return;
            }

            if (TextUtils.isEmpty(cvv)) {
                editCvv.setError("Ingresa el CVV");
                return;
            }

            if (!isValidCvv(cvv)) {
                editCvv.setError("CVV inválido");
                return;
            }



            // Simular el proceso de pago y mostrar el mensaje de confirmación
            if (simulatePayment()) {
                showConfirmationDialog();
            }
        }
    });
}

    private boolean isValidCardNumber(String cardNumber) {
        // Eliminar guiones y espacios en blanco del número de tarjeta
        cardNumber = cardNumber.replace("-", "").replace(" ", "");

        // Verificar que el número de tarjeta tenga exactamente 16 dígitos
        if (cardNumber.length() != 16) {
            return false;
        }



        // La expresión regular valida números de tarjeta de crédito y débito de 16 dígitos
        return cardNumber.matches("^\\d{16}$");
    }



    private boolean isValidExpirationDate(String expirationDate) {
        // Utilizar expresión regular para verificar que la fecha de vencimiento tenga el formato MM/AA
        // La expresión regular valida MM/AA donde MM es de 01 a 12 y AA es de 00 a 99
        return expirationDate.matches("^(0[1-9]|1[0-2])/\\d{2}$");
    }

    private boolean isValidCvv(String cvv) {
        // Verificar que el CVV tenga exactamente 3 dígitos
        return cvv.length() == 3 && cvv.matches("\\d{3}");
    }


    // Método para simular el proceso de pago
    private boolean simulatePayment() {

        // Devuelve true si el pago es exitoso, false en caso contrario
        return true;
    }

    // Método para mostrar el mensaje de confirmación como un AlertDialog
    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.dialog_confirmation);
        builder.setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Cerrar el AlertDialog cuando se hace clic en el botón "Cerrar"
                dialog.dismiss();
                //Redireccionar a la pagina de inicio
                Intent intent = new Intent(PagoActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
