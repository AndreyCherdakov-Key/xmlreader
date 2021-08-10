package com.jobs;

import com.config.Config;
import com.domain.Root;
import com.services.IXMLReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Component
public class JobLoadXML implements IJobLoadXML {

    final private IXMLReader ixmlReader;

    @Autowired
    public JobLoadXML(IXMLReader ixmlReader) {
        this.ixmlReader = ixmlReader;
    }

    @Override
    @Scheduled(fixedDelay = Config.DURATION)
    public void loadXML() {

        XMLInputFactory inputFactory = XMLInputFactory.newFactory();

        /* Using JAXB and StAX for streaming read XML
        in the XML I m reading Root and put it into Root.class, everything that is inside the Root.class I also put
        into other dependent classes (Event.class, Product.class and RequestDetails.class)
         */
        try (FileInputStream inputStream = new FileInputStream(Config.XML_PATH)) {
            XMLEventReader xmlEventReader = inputFactory.createXMLEventReader(inputStream);
            JAXBContext jaxbContext = JAXBContext.newInstance(Root.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            while (xmlEventReader.hasNext()) {
                /* I must to check current element is a Start element?
                and name of that element is equal Root?
                 */
                if (xmlEventReader
                        .peek()
                        .isStartElement() &&
                        xmlEventReader
                                .peek()
                                .asStartElement()
                                .getName()
                                .getLocalPart()
                                .equals(Config.ROOT)) {
                    Root root = unmarshaller.unmarshal(xmlEventReader, Root.class).getValue();
                    System.out.println(root.toString());
                    saveToTheDB(root);
                } else {
                    xmlEventReader.nextEvent();
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
            e.printStackTrace();
        } catch (IOException | XMLStreamException e) {
            System.err.println("IOException or XMLStreamException");
            e.printStackTrace();
        } catch (JAXBException e) {
            System.err.println("JAXBException");
            e.printStackTrace();
        }
    }

    private void saveToTheDB(Root root) {
        ixmlReader.save(root);
    }
}
