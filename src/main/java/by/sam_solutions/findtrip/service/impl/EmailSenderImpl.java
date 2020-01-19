package by.sam_solutions.findtrip.service.impl;

import by.sam_solutions.findtrip.config.EmailConfig;
import by.sam_solutions.findtrip.controller.dto.OrderDTO;
import by.sam_solutions.findtrip.service.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderImpl implements EmailSender {

    private final String APP_MAIL = "findtripautosender@gmail.com";

    private EmailConfig emailConfig;
    private JavaMailSenderImpl mailSender;

    @Autowired
    public EmailSenderImpl(EmailConfig emailConfig) {
        this.emailConfig = emailConfig;
        this.mailSender = new JavaMailSenderImpl();

        mailSender.setHost(this.emailConfig.getHost());
        mailSender.setPort(this.emailConfig.getPort());
        mailSender.setUsername(this.emailConfig.getUsername());
        mailSender.setPassword(this.emailConfig.getPassword());
    }

    @Override
    public void sendСonfirmPurchaseToEmail(OrderDTO orderDTO) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(APP_MAIL);
        mailMessage.setTo(orderDTO.getUserDTO().getEmail());
        mailMessage.setSubject("Your tickets purchase Order #" + orderDTO.getId() + " on findtrip.com");
        mailMessage.setText("Hello, Dear friend.\n" +
                "Your Order #" + orderDTO.getId() + " has been successfully paid.\n" +
                "Final cost($): " + orderDTO.getFinalCost() + '\n' +
                "Count tickets: " + orderDTO.getTicketDTOList().size() + '\n' +
                "Order date: " + orderDTO.getOrderDate() + '\n' +
                "User: " + orderDTO.getUserDTO().getLogin() + '\n' +
                "Telephone: " + orderDTO.getUserDTO().getPhoneNumber() + '\n' +
                "\n\n\n\n" +
                "Printing tickets in the infokiosk by order number." + '\n' +
                "-------------------------------------------------------------------\n" +
                "This message was automatically generated. Please do not reply to this message.");

        mailSender.send(mailMessage);
    }

    @Override
    public void sendСancellationСonfirmToEmail(OrderDTO orderDTO) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(APP_MAIL);
        mailMessage.setTo(orderDTO.getUserDTO().getEmail());
        mailMessage.setSubject("Your  Order #" + orderDTO.getId() + " on findtrip.com has been canceled");
        mailMessage.setText("Hello, Dear friend.\n" +
                "Your Order #" + orderDTO.getId() + " has been canceled.\n" +
                "Money($) " + orderDTO.getFinalCost() + " has already been returned to your wallet.\n" +
                "-------------------------------------------------------------------\n" +
                "This message was automatically generated. Please do not reply to this message.");

        mailSender.send(mailMessage);
    }
}
