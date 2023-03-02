/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package raven.cell;

/**
 *
 * @author thinh nguyen
 */
public interface TableDownloadUploadEvent {

    public void download (int row);

    public void upload (int row);
}
