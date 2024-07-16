import { FlutterIntegrationDriverService } from './service.js';
import { ChainablePromiseElement } from 'webdriverio';
export default FlutterIntegrationDriverService;

declare global {
  namespace WebdriverIO {
    interface Browser {
      flutterByValueKey(value: string): Promise<Flutter.Locator>;
      flutterByValueKey$(
        value: string,
      ): ChainablePromiseElement<WebdriverIO.Element>;
      flutterByValueKey$$(
        value: string,
      ): ChainablePromiseElement<WebdriverIO.Element[]>;
      flutterBySemanticsLabel(label: string): Promise<Flutter.Locator>;
      flutterBySemanticsLabel$(
        label: string,
      ): ChainablePromiseElement<WebdriverIO.Element>;
      flutterBySemanticsLabel$$(
        label: string,
      ): ChainablePromiseElement<WebdriverIO.Element[]>;
      flutterByText(text: string): Promise<Flutter.Locator>;
      flutterByText$(
        text: string,
      ): ChainablePromiseElement<WebdriverIO.Element>;
      flutterByType(text: string): Promise<Flutter.Locator>;
      flutterByType$(
        text: string,
      ): ChainablePromiseElement<WebdriverIO.Element>;
      flutterByType$$(
        text: string,
      ): ChainablePromiseElement<WebdriverIO.Element[]>;
      flutterByText$$(
        text: string,
      ): ChainablePromiseElement<WebdriverIO.Element[]>;
      flutterWaitForVisible(options: {
        element: WebdriverIO.Element;
        timeout?: number;
      }): Promise<void>;
      flutterDoubleClick(options: {
        element: WebdriverIO.Element;
        offset?: { x: number; y: number };
      }): WebdriverIO.Element;
      flutterLongPress(options: {
        element: WebdriverIO.Element;
        offset?: { x: number; y: number };
      }): WebdriverIO.Element;
      flutterWaitForAbsent(options: {
        element: WebdriverIO.Element;
        timeout?: number;
      }): Promise<void>;

      flutterScrollTillVisible(options: {
        finder: WebdriverIO.Element;
        scrollView?: WebdriverIO.Element;
        scrollDirection?: 'up' | 'right' | 'down' | 'left';
        delta?: number;
        maxScrolls?: number;
        settleBetweenScrollsTimeout?: number;
        dragDuration?: number;
      }): ChainablePromiseElement<WebdriverIO.Element | null>;

      flutterDragAndDrop(options: {
        source: WebdriverIO.Element;
        target: WebdriverIO.Element;
      }): Promise<void>;
    }
    interface Element {
      flutterByValueKey(value: string): Promise<Flutter.Locator>;
      flutterByValueKey$(value: string): WebdriverIO.Element;
      flutterByValueKey$$(value: string): WebdriverIO.Element[];
      flutterBySemanticsLabel(label: string): Promise<Flutter.Locator>;
      flutterBySemanticsLabel$(label: string): WebdriverIO.Element;
      flutterBySemanticsLabel$$(label: string): WebdriverIO.Element[];
      flutterByText(text: string): Promise<Flutter.Locator>;
      flutterByText$(text: string): WebdriverIO.Element;
      flutterByText$$(text: string): WebdriverIO.Element[];
      flutterByType(text: string): Promise<Flutter.Locator>;
      flutterByType$(
        text: string,
      ): ChainablePromiseElement<WebdriverIO.Element>;
      flutterByType$$(
        text: string,
      ): ChainablePromiseElement<WebdriverIO.Element[]>;
    }
  }

  namespace Flutter {
    // @ts-ignore
    type Locator = {
      using: string;
      value: string;
    };

    // @ts-ignore
    type Point = {
      x: number;
      y: number;
    };
  }
}

export { FlutterIntegrationDriverService };
