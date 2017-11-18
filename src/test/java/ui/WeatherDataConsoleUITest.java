package ui;

import io.ConsoleReader;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import repository.WeatherRepository;
import weatherrequest.WeatherRequest;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class WeatherDataConsoleUITest {
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

        WeatherDataConsoleUI weatherDataConsoleUI = new WeatherDataConsoleUI(consoleReaderMock, null);
        try {
            weatherDataConsoleUI.start();
        } catch (NullPointerException ignored) {
        }

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

        WeatherDataConsoleUI weatherDataConsoleUI = new WeatherDataConsoleUI(consoleReaderMock, null);
        try {
            weatherDataConsoleUI.start();
        } catch (NullPointerException ignored) {
        }

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

        WeatherDataConsoleUI weatherDataConsoleUI = new WeatherDataConsoleUI(consoleReaderMock, null);
        try {
            weatherDataConsoleUI.start();
        } catch (NullPointerException ignored) {
        }

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

        WeatherDataConsoleUI weatherDataConsoleUI = new WeatherDataConsoleUI(consoleReaderMock, null);
        try {
            weatherDataConsoleUI.start();
        } catch (NullPointerException ignored) {
        }

        verify(consoleReaderMock, times(4)).readValueFromConsole(anyString());
    }

    @Test
    public void testWeatherRepositoryMethodsInvoked() {
        WeatherRepository weatherRepositoryMock = mock(WeatherRepository.class);
        when(weatherRepositoryMock.getWeatherForecastReport(any(WeatherRequest.class))).thenReturn(null);
        when(weatherRepositoryMock.getCurrentWeatherReport(any(WeatherRequest.class))).thenReturn(null);
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

        WeatherDataConsoleUI weatherDataConsoleUI = new WeatherDataConsoleUI(consoleReaderMock, weatherRepositoryMock);
        try {
            weatherDataConsoleUI.start();
        } catch (NullPointerException ignored) {
        }

        InOrder inOrder = inOrder(weatherRepositoryMock);

        inOrder.verify(weatherRepositoryMock, times(1)).getCurrentWeatherReport(any(WeatherRequest.class));
        inOrder.verify(weatherRepositoryMock, times(1)).getWeatherForecastReport(any(WeatherRequest.class));
    }
}