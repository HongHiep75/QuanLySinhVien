package quan.ly.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import danh.sach.DanhSachMon;
import model.MonHoc;

public class FileIODanhSachMonHoc {

//  ghi danh sach mon hoc vao file	
	public static boolean writeFileDanhSachMonHoc(MonHoc[] list) {

		if (list == null) {
			return false;
		}
		int n = list.length;
		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/file/DanhSachMonHoc"))) {

			for (int i = 0; i < n; i++) {
				bufferedWriter.write(list[i].toString());
				bufferedWriter.newLine();
			}
			return true;
		} catch (FileNotFoundException e) {
			System.out.println("Không tìm thấy file");
			return false;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	// doc file danh sach mon hoc
	public static DanhSachMon readFileDanhSachMonHoc() {
		DanhSachMon danhSach = new DanhSachMon();
		MonHoc monHoc;

		try (BufferedReader br = new BufferedReader(new FileReader("src/file/DanhSachMonHoc"))) {
			String line = "";
			while (true) {
				line = br.readLine();
				if (line == null) {
					break;
				}
				// cat du lieu tai vi tri " "
				String data[] = line.split("-");
				String id = data[0];
				String name = data[1];
				String tinChi = data[2];
				String loaiMon = data[3];
//				// ep kieu du lieu ve int va double
				int maSV = Integer.parseInt(id);
				double tin = Double.parseDouble(tinChi);
				monHoc = new MonHoc(maSV, name, tin, loaiMon);
				danhSach.themMonHoc(monHoc);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Không tìm thấy file");
			return null;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return null;
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Dữ liệu trong file không khớp");
			return null;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return danhSach;
	}
}
