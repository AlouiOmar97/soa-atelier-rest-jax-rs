package tn.esprit.atelierrest1.services;

import tn.esprit.atelierrest1.entities.Option;

import java.util.ArrayList;
import java.util.List;

public class OptionService {

    private static final List<Option> options = new ArrayList<>();

    static {
        options.add(new Option(
                1,
                "Informatique",
                "Informatique",
                "M. Responsable Informatique",
                30,
                1,
                30
        ));

        options.add(new Option(
                2,
                "Mathématiques",
                "Mathématiques",
                "Mme Responsable Mathématiques",
                25,
                1,
                25
        ));
    }

    public List<Option> getAllOptions() {
        return options;
    }

    public Option getOptionById(int codeOption) {
        for (Option option : options) {
            if (option.getCodeOption() == codeOption) {
                return option;
            }
        }

        return null;
    }

    public List<Option> getOptionsByDomaine(String domaine) {
        List<Option> result = new ArrayList<>();

        for (Option option : options) {
            if (option.getDomaine().equalsIgnoreCase(domaine)) {
                result.add(option);
            }
        }

        return result;
    }

    public boolean addOption(Option option) {
        if (getOptionById(option.getCodeOption()) != null) {
            return false;
        }

        options.add(option);
        return true;
    }

    public Option updateOption(int codeOption, Option updatedOption) {

        Option existingOption = getOptionById(codeOption);

        if (existingOption == null) {
            return null;
        }

        updatedOption.setCodeOption(codeOption);

        int index = options.indexOf(existingOption);
        options.set(index, updatedOption);

        return updatedOption;
    }

    public boolean deleteOption(int codeOption) {
        Option option = getOptionById(codeOption);

        if (option == null) {
            return false;
        }

        options.remove(option);
        return true;
    }
}