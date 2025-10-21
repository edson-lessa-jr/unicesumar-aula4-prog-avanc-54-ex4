package br.com.exemplo.cdi;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named("cdiProbe")
@ApplicationScoped
public class CdiProbe {
    public String getStatus() { return "ok"; }
}
