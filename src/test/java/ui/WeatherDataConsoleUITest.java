package ui;

import city.Coordinates;
import io.ConsoleReader;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import repository.WeatherRepository;
import utility.Constants;
import weatherdata.CurrentWeatherReport;
import weatherdata.ForecastOneDayWeather;
import weatherdata.WeatherForecastReport;
import weatherrequest.WeatherRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class WeatherDataConsoleUITest {
    private WeatherRepository repositoryMock;

    @Before
    public void setupRepository() {
        repositoryMock = mock(WeatherRepository.class);

        CurrentWeatherReport currentWeatherReportMock = mock(CurrentWeatherReport.class);
        when(currentWeatherReportMock.getCityName()).thenReturn("DummyName");
        when(currentWeatherReportMock.getTemperatureUnit()).thenReturn(Constants.TemperatureUnits.STANDARD);
        when(currentWeatherReportMock.getCoordinates()).thenReturn(Coordinates.of(1, 2));
        when(currentWeatherReportMock.getCountryCode()).thenReturn("US");

        WeatherForecastReport weatherForecastReportMock = mock(WeatherForecastReport.class);
        when(weatherForecastReportMock.getCityName()).thenReturn("DummyName");
        when(weatherForecastReportMock.getTemperatureUnit()).thenReturn(Constants.TemperatureUnits.STANDARD);
        when(weatherForecastReportMock.getCoordinates()).thenReturn(Coordinates.of(1, 2));
        when(weatherForecastReportMock.getCountryCode()).thenReturn("US");

        List<ForecastOneDayWeather> forecastOneDayWeatherList = new ArrayList<>();
        forecastOneDayWeatherList.add(new ForecastOneDayWeather(LocalDate.now(), 10, 20));
        forecastOneDayWeatherList.add(new ForecastOneDayWeather(LocalDate.now(), 33, 66));
        forecastOneDayWeatherList.add(new ForecastOneDayWeather(LocalDate.now(), 44, 77));

        when(weatherForecastReportMock.getOneDayWeathers()).thenReturn(forecastOneDayWeatherList);

        when(repositoryMock.getCurrentWeatherReport(any(WeatherRequest.class))).thenReturn(currentWeatherReportMock);
        when(repositoryMock.getWeatherForecastReport(any(WeatherRequest.class))).thenReturn(weatherForecastReportMock);
    }

    @Test
    public void testConsoleReaderInvokedTwoTimesIfNameIsIncorrect() {
        ConsoleReader consoleReaderMock = mock(ConsoleReader.class);

        when(consoleReaderMock.readValueFromConsole(anyString())).thenAnswer(new Answer() {
            private int count = 0;
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                switch (count++) {
                    case 0:
                        return "";

                    case 1:
                        return "Tallinn";

                    case 2:
                        return "n";

                    default:
                        return null;
                }
            }
        });

        WeatherDataConsoleUI weatherDataConsoleUI = new WeatherDataConsoleUI(consoleReaderMock, repositoryMock);

        weatherDataConsoleUI.start();

        verify(consoleReaderMock, times(3)).readValueFromConsole(anyString());
    }

    @Test
    public void testConsoleReaderInvokedTwoTimesIfTempUnitIsNotChanged() {
        ConsoleReader consoleReaderMock = mock(ConsoleReader.class);

        when(consoleReaderMock.readValueFromConsole(anyString())).thenAnswer(new Answer() {
            private int count = 0;
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                switch (count++) {
                    case 0:
                        return "Tallinn";

                    case 1:
                        return "n";

                    default:
                        return null;
                }
            }
        });

        WeatherDataConsoleUI weatherDataConsoleUI = new WeatherDataConsoleUI(consoleReaderMock, repositoryMock);
        weatherDataConsoleUI.start();

        verify(consoleReaderMock, times(2)).readValueFromConsole(anyString());
    }

    @Test
    public void testConsoleReaderInvoked3TimesIfTempUnitChanged() {
        ConsoleReader consoleReaderMock = mock(ConsoleReader.class);

        when(consoleReaderMock.readValueFromConsole(anyString())).thenAnswer(new Answer() {
            private int count = 0;
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                switch (count++) {
                    case 0:
                        return "Tallinn";

                    case 1:
                        return "y";

                    case 2:
                        return "metric";

                    default:
                        return null;
                }
            }
        });

        WeatherDataConsoleUI weatherDataConsoleUI = new WeatherDataConsoleUI(consoleReaderMock, repositoryMock);
        weatherDataConsoleUI.start();

        verify(consoleReaderMock, times(3)).readValueFromConsole(anyString());
    }

    @Test
    public void testConsoleReaderInvoked4TimesIfTempUnitIsIncorrect() {
        ConsoleReader consoleReaderMock = mock(ConsoleReader.class);

        when(consoleReaderMock.readValueFromConsole(anyString())).thenAnswer(new Answer() {
            private int count = 0;

            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                switch (count++) {
                    case 0:
                        return "Tallinn";

                    case 1:
                        return "y";

                    case 2:
                        return "aaa";

                    case 3:
                        return "metric";

                    default:
                        return null;
                }
            }
        });

        WeatherDataConsoleUI weatherDataConsoleUI = new WeatherDataConsoleUI(consoleReaderMock, repositoryMock);
        weatherDataConsoleUI.start();

        verify(consoleReaderMock, times(4)).readValueFromConsole(anyString());
    }

    @Test
    public void testWeatherRepositoryMethodsInvoked() {
        ConsoleReader consoleReaderMock = mock(ConsoleReader.class);
        when(consoleReaderMock.readValueFromConsole(anyString())).thenAnswer(new Answer() {
            private int count = 0;
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                switch (count++) {
                    case 0:
                        return "Tallinn";

                    case 1:
                        return "n";

                    default:
                        return null;
                }
            }
        });

        WeatherDataConsoleUI weatherDataConsoleUI = new WeatherDataConsoleUI(consoleReaderMock, repositoryMock);
        weatherDataConsoleUI.start();

        InOrder inOrder = inOrder(repositoryMock);

        inOrder.verify(repositoryMock, times(1)).getCurrentWeatherReport(any(WeatherRequest.class));
        inOrder.verify(repositoryMock, times(1)).getWeatherForecastReport(any(WeatherRequest.class));
    }
}