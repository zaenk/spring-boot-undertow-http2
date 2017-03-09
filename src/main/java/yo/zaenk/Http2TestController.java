package yo.zaenk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Controller
public class Http2TestController {

	private RestTemplate restTemplate;

	@Autowired
	public Http2TestController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@GetMapping("test")
	public String test(Model viewModel, HttpServletRequest request) {
		String response = restTemplate.getForObject("https://http2.akamai.com/", String.class);
		viewModel.addAttribute("outbound", response.contains("You are using HTTP/2 right now!"));
		viewModel.addAttribute("inbound", request.getProtocol().toLowerCase().equals("http/2.0"));
		return "test";
	}
}
