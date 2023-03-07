package pidev.afarshop.utils;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import pidev.afarshop.Entity.Product;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class QRCodeGenerator {

    public static void generateQRCode(Product product) throws WriterException, IOException {
        String qrCodePath = "C:\\Users\\Ismail\\Documents\\GitHub\\AFARSHOP\\Back_End_Spring\\src\\main\\QRCode\\";
        String qrCodeName = qrCodePath+product.getProductName()+product.getProductId()+"-QRCODE.png";
        var qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix =qrCodeWriter.encode("https://www.zara.com/tn/fr/sweat---capuche-babar-p00962410.html?v1=247172014"+"\n",
                BarcodeFormat.QR_CODE,200,200);
        Path path = FileSystems.getDefault().getPath(qrCodeName);
        MatrixToImageWriter.writeToPath(bitMatrix,"PNG",path);

    }
}