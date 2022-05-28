package lav.valentine.soapclient.controller;

import lav.valentine.soapclient.client.SoapClient;
import lav.valentine.soapclient.jaxb.GetAllRecordsResponse;
import lav.valentine.soapclient.jaxb.GetCurrencyRateResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CurrencyController {
    private final SoapClient soapClient;

    public CurrencyController(SoapClient soapClient) {
        this.soapClient = soapClient;
    }

    @GetMapping("/")
    public String getMain(Model model) {
        return "index";
    }

    @PostMapping(value = "/get-record")
    public String getDateAndTime(@RequestParam String date,
                                 @RequestParam String currency, Model model) throws Exception {

        GetCurrencyRateResponse getCurrencyRateResponse = soapClient.getCursOnDate(date, currency);
        model.addAttribute("currency", getCurrencyRateResponse);

        return "get-record";
    }

    @PostMapping(value = "/get-all-records")
    public String getAllRecords(Model model) throws Exception {

        GetAllRecordsResponse getAllRecordsResponse = soapClient.getAllRecordsResponse();
        model.addAttribute("records", getAllRecordsResponse.getRecords());

        return "get-all-records";
    }
}