package org.ex;

import checkbox.Checkbox;
import checkbox.Radio;
import dropdown.Dropdown;
import dropdown.MultipleSelection;
import dropdown.SearchSelection;
import dropdown.Selection;
import frame.FrameTest;
import frame.NestedFrame;
import popup.Modal;
import popup.PopupTest;
import uploadanddownload.UploadDownload;
import windows.Windows;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Menu:");
            System.out.println("1. Test Checkbox");
            System.out.println("2. Test radio");
            System.out.println("3. Test Dropdown");
            System.out.println("4. Test MultipleSelection");
            System.out.println("5. Test Selection");
            System.out.println("6. Test SearchSelection");
            System.out.println("7. Test Popup");
            System.out.println("8. Test Modal");
            System.out.println("9. Test Frame");
            System.out.println("10. Test IFrame");
            System.out.println("11. Test Windows");
            System.out.println("12. Test Upload and Download");
            System.out.println("0. Thoát");
            System.out.print("Chọn một tùy chọn: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Checkbox checkbox = new Checkbox();
                    checkbox.checkCheckbox();
                    break;
                case 2:
                    Radio radio = new Radio();
                    radio.checkRadio();
                    break;
                case 3:
                    Dropdown dropdown = new Dropdown();
                    dropdown.checkDropdown();
                    break;
                case 4:
                    MultipleSelection multipleSelection = new MultipleSelection();
                    multipleSelection.checkMultipleSelenium();
                    break;
                case 5:
                    Selection selection = new Selection();
                    selection.checkSelection();
                    break;
                case 6:
                    SearchSelection searchSelection = new SearchSelection();
                    searchSelection.checkSearchSelection();
                    break;
                case 7:
                    PopupTest popupTest = new PopupTest();
                    popupTest.checkPopup();
                    break;
                case 8:
                    Modal modal = new Modal();
                    modal.testModalStandard();
                    modal.testModalBasic();
                    break;
                case 9:
                    FrameTest frame = new FrameTest();
                    frame.frameCheck();
                    break;
                case 10:
                    NestedFrame nestedFrame = new NestedFrame();
                    nestedFrame.nestedFrameCheck();
                    break;
                case 11:
                    Windows windows = new Windows();
                    windows.testNewTab();
                    windows.testNewWindow();
                    windows.testNewWindowMessage();
                    break;
                case 12:
                    UploadDownload uploadDownload = new UploadDownload();
                    uploadDownload.uploadDownloadCheck();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Tùy chọn không hợp lệ. Vui lòng thử lại.");
            }
        }

        scanner.close();
    }
}