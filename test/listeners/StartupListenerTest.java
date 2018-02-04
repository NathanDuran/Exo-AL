package listeners;

import org.junit.*;
import org.mockito.*;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;
import weka.core.converters.ConverterUtils.DataSource;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 *
 * @author Nate
 */
public class StartupListenerTest {

    public StartupListenerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Mock
    ServletContextEvent mockEvent = mock(ServletContextEvent.class);
    @Mock
    ServletContext mockServletContext = mock(ServletContext.class);
    @Mock
    InputStream mockStream = mock(InputStream.class);
    @Mock
    DataSource mockSource = mock(ConverterUtils.DataSource.class);

    /**
     * Test of contextInitialized method, of class StartupListener.
     */
    @Test
    public void testContextInitialized() {
        System.out.println("contextInitialized");

        when(mockEvent.getServletContext()).thenReturn(mockServletContext);
        when(mockServletContext.getInitParameter(Matchers.anyString())).thenReturn("");
        doNothing().when(mockServletContext).setAttribute(Matchers.anyString(), Matchers.any());

        //Call starup listener
        StartupListener instance = new StartupListener();
        instance.contextInitialized(mockEvent);

        //Verify context has been initialised
        verify(mockEvent, times(1)).getServletContext();
        //Verify initParameters are retreived
        verify(mockServletContext, atLeast(4)).getInitParameter(Matchers.anyString());
        //Verify attributes are set
        verify(mockServletContext, times(9)).setAttribute(Matchers.anyString(), Matchers.any());
    }

    /**
     * Test of getDataset method, of class StartupListener.
     */
    @Test
    public void testGetDataset() {
        System.out.println("getDataset");

        try {
            when(mockServletContext.getResourceAsStream(Matchers.anyString())).thenReturn(mockStream);
            doReturn(mock(Instances.class)).when(mockSource).getDataSet();

            //Call starup listener
            StartupListener instance = new StartupListener();
            instance.getDataset(mockServletContext, Matchers.anyString());

            //Ensure getDatSet returns Instances
            assertThat(mockSource.getDataSet(), instanceOf(Instances.class));
            //Verify getResource and getDataSet are called at leat once
            verify(mockServletContext, times(1)).getResourceAsStream(Matchers.anyString());
            verify(mockSource, times(1)).getDataSet();

        } catch (Exception ex) {
            System.err.println("StartupListenerTest testGetDataset exception: " + ex);
        }
    }

}
