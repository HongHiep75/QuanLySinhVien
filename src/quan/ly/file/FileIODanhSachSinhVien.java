package quan.ly.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import danh.sach.DanhSachSinhVien;
import model.SinhVien;

public class FileIODanhSachSinhVien {
//  ghi danh sach sinh vien vao file	
	public static boolean writeFileDanhSachSinhVien(SinhVien[] list) {

		if (list == null) {
			return false;
		}
		int n = list.length;
		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/file/DanhSachSinhVien"))) {

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
		}
	}

	// doc file danh sach sinh vien
	public static DanhSachSinhVien readFileDanhSachSinhVien() {
		DanhSachSinhVien danhSach = new DanhSachSinhVien();
		SinhVien sinhVien;

		try (BufferedReader br = new BufferedReader(new FileReader("src/file/DanhSachSinhVien"))) {
			String line = null;
			while (true) {
				line = br.readLine();
				if (line == null) {
					break;
				}
				// cat du lieu tai vi tri " " thành một mảng
				// lọc giá trị tương ứng rồi tạo đối tượng sinh viên
				String data[] = line.split("-");
				String id = data[0];
				String name = data[1];
				String diaChi = data[2];
				String soDienThoai = data[3];
				String lop = data[4];
//				// ep kieu du lieu ve int va double
				int maSV = Integer.parseInt(id);
				sinhVien = new SinhVien(maSV, name, diaChi, soDienThoai, lop);
				danhSach.themSinhVien(sinhVien);
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
