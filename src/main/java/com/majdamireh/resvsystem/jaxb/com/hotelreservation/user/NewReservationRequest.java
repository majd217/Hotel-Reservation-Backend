//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.3 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
//


package com.hotelreservation.user;

import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType>
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="hotelid" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="checkInDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         <element name="checkOutDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         <element name="numberOfRooms" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="numberOfPeople" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "hotelid",
    "checkInDate",
    "checkOutDate",
    "numberOfRooms",
    "numberOfPeople"
})
@XmlRootElement(name = "NewReservationRequest")
public class NewReservationRequest {

    protected int hotelid;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar checkInDate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar checkOutDate;
    protected int numberOfRooms;
    protected int numberOfPeople;

    /**
     * Gets the value of the hotelid property.
     * 
     */
    public int getHotelid() {
        return hotelid;
    }

    /**
     * Sets the value of the hotelid property.
     * 
     */
    public void setHotelid(int value) {
        this.hotelid = value;
    }

    /**
     * Gets the value of the checkInDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCheckInDate() {
        return checkInDate;
    }

    /**
     * Sets the value of the checkInDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCheckInDate(XMLGregorianCalendar value) {
        this.checkInDate = value;
    }

    /**
     * Gets the value of the checkOutDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCheckOutDate() {
        return checkOutDate;
    }

    /**
     * Sets the value of the checkOutDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCheckOutDate(XMLGregorianCalendar value) {
        this.checkOutDate = value;
    }

    /**
     * Gets the value of the numberOfRooms property.
     * 
     */
    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    /**
     * Sets the value of the numberOfRooms property.
     * 
     */
    public void setNumberOfRooms(int value) {
        this.numberOfRooms = value;
    }

    /**
     * Gets the value of the numberOfPeople property.
     * 
     */
    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    /**
     * Sets the value of the numberOfPeople property.
     * 
     */
    public void setNumberOfPeople(int value) {
        this.numberOfPeople = value;
    }

}
